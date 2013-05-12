/**
 * Copyright 2012 Muzima Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mclinic.api.service.impl;

import com.google.inject.Inject;
import com.mclinic.api.annotation.Authorization;
import com.mclinic.api.dao.CohortDao;
import com.mclinic.api.dao.MemberDao;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.model.Member;
import com.mclinic.api.service.CohortService;
import com.mclinic.api.service.PatientService;
import com.mclinic.util.Constants;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

public class CohortServiceImpl implements CohortService {

    @Inject
    private CohortDao cohortDao;

    @Inject
    private MemberDao memberDao;

    @Inject
    private PatientService patientService;

    protected CohortServiceImpl() {
    }

    /**
     * Download a single cohort record from the cohort rest resource into the local lucene repository.
     *
     * @param uuid the uuid of the cohort.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download cohort with matching uuid.
     */
    @Override
    @Authorization(privileges = {"View Cohort Privilege"})
    public Cohort downloadCohortByUuid(final String uuid) throws IOException, ParseException {
        cohortDao.download(uuid, Constants.UUID_COHORT_RESOURCE);
        return getCohortByUuid(uuid);
    }

    /**
     * Download all cohorts with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the cohort to be downloaded. When empty, will return all cohorts available.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download all cohort with partially matched name.
     * @should download all cohort when name is empty.
     */
    @Override
    @Authorization(privileges = {"View Cohort Privilege"})
    public List<Cohort> downloadCohortsByName(final String name) throws IOException, ParseException {
        cohortDao.download(name, Constants.SEARCH_COHORT_RESOURCE);
        return getCohortsByName(name);
    }

    /**
     * Get a single cohort record from the repository using the uuid.
     *
     * @param uuid the cohort uuid.
     * @return cohort with matching uuid or null when no cohort match the uuid.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return cohort with matching uuid.
     * @should return null when no cohort match the uuid.
     */
    @Override
    @Authorization(privileges = {"View Cohort Privilege"})
    public Cohort getCohortByUuid(final String uuid) throws IOException, ParseException {
        return cohortDao.getByUuid(uuid);
    }

    /**
     * Get list of cohorts based on the name of the cohort. If empty string is passed, it will search for all cohorts.
     *
     * @param name the partial name of the cohort.
     * @return list of all cohorts with matching uuid or empty list when no cohort match the name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return list of all cohorts with matching name.
     * @should return empty list when no cohort match the name.
     */
    @Override
    @Authorization(privileges = {"View Cohort Privilege"})
    public List<Cohort> getCohortsByName(final String name) throws IOException, ParseException {
        return cohortDao.getByName(name);
    }

    /**
     * @return all registered cohort or empty list when no cohort is registered.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return all registered cohorts.
     * @should return empty list when no cohort is registered.
     */
    @Override
    @Authorization(privileges = {"View Cohort Privilege"})
    public List<Cohort> getAllCohorts() throws IOException, ParseException {
        return cohortDao.getAll();
    }

    /**
     * Delete a single cohort record from the repository.
     *
     * @param cohort the cohort to be deleted.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should delete the cohort from lucene repository.
     */
    @Override
    public void deleteCohort(final Cohort cohort) throws IOException, ParseException {
        try {
            cohortDao.delete(cohort, Constants.SEARCH_COHORT_RESOURCE);
        } finally {
            cohortDao.delete(cohort, Constants.UUID_COHORT_RESOURCE);
        }
    }

    /**
     * Download all patients' uuid under the current cohort identified by the cohort uuid and save them in to the local
     * repository.
     *
     * @param cohortUuid the cohort's uuid.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download all patients from the current cohort identified by the cohort's uuid.
     */
    @Override
    public List<Member> downloadMembers(final String cohortUuid) throws IOException, ParseException {
        memberDao.download(cohortUuid, Constants.MEMBER_COHORT_RESOURCE);
        return getMembers(cohortUuid);
    }

    /**
     * Get all patients' uuid under the current cohort identified by the cohort's uuid which already saved in the local
     * repository.
     *
     * @param cohortUuid the cohort's uuid.
     * @return list of all patients' uuid under current cohort uuid or empty list when no patient are in the cohort.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return list of all patients for the cohort.
     * @should return empty list when no patient are in the cohort.
     */
    @Override
    public List<Member> getMembers(final String cohortUuid) throws IOException, ParseException {
        return memberDao.getByCohortUuid(cohortUuid);
    }

    /**
     * Delete all members for the current cohort identified by the cohort's uuid.
     *
     * @param cohortUuid the cohort's uuid.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should delete all patients for the cohort from the local repository.
     */
    @Override
    public void deleteMembers(final String cohortUuid) throws IOException, ParseException {
        for (Member member : getMembers(cohortUuid)) {
            memberDao.delete(member, Constants.MEMBER_COHORT_RESOURCE);
        }
    }
}
