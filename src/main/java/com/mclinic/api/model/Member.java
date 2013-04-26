/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package com.mclinic.api.model;

import com.mclinic.search.api.model.object.BaseSearchable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an exception from all of the other model classes where it's a local object but have the name similar
 * with the same remote resource. This class must not be associated with the <code>MemberCohortResolver</code>.
 */
public class Member extends BaseSearchable {

    private String uuid;

    private String cohortUuid;

    private List<String> patientUuidList;

    private String checksum;

    /**
     * Get the uuid for the cohort member.
     *
     * @return the uuid.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the uuid for the cohort member.
     *
     * @param uuid the uuid to set.
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     * Get the cohort uuid associated with this cohort member.
     *
     * @return the cohort uuid associated with this cohort member.
     */
    public String getCohortUuid() {
        return cohortUuid;
    }

    /**
     * Set the cohort uuid associated with this cohort member.
     *
     * @param cohortUuid the cohort uuid associated with this cohort member.
     */
    public void setCohortUuid(final String cohortUuid) {
        this.cohortUuid = cohortUuid;
    }

    /**
     * Add new patient's uuid in this cohort member record.
     *
     * @param uuid the patient uuid.
     */
    public void addPatient(final String uuid) {
        getPatients().add(uuid);
    }

    /**
     * Get the list of patient uuid for the cohort.
     *
     * @return the list of patient uuid.
     */
    public List<String> getPatients() {
        if (patientUuidList == null)
            patientUuidList = new ArrayList<String>();
        return patientUuidList;
    }

    /**
     * Set the list of patient uuid for the cohort.
     *
     * @param patientUuidList the list of patient uuid.
     */
    public void setPatients(final List<String> patientUuidList) {
        this.patientUuidList = patientUuidList;
    }

    /**
     * Get the checksum for the searchable object.
     *
     * @return the searchable object's checksum.
     */
    @Override
    public String getChecksum() {
        return checksum;
    }

    /**
     * Set the checksum for the searchable object.
     *
     * @param checksum the checksum for the searchable object.
     */
    @Override
    public void setChecksum(final String checksum) {
        this.checksum = checksum;
    }
}
