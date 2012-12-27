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
package com.mclinic.api.service;

import java.io.File;
import java.net.URL;
import java.util.List;

import com.mclinic.api.model.Cohort;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CohortServiceTest {

    private CohortService cohortService;

    private AdministrativeService service;

    @Before
    public void prepare() throws Exception {
        URL repositoryPath = AdministrativeServiceTest.class.getResource("../j2l");
        URL lucenePath = AdministrativeServiceTest.class.getResource("../lucene");
        Context.initialize(new MuzimaModule(lucenePath.getPath(), "uuid"));

        service = Context.getInstance(AdministrativeService.class);
        Assert.assertNotNull(service);

        service.initializeRepository(repositoryPath.getPath());

        URL jsonPath = AdministrativeServiceTest.class.getResource("../json/cohort");
        service.loadCohorts(new File(jsonPath.getPath()));

        cohortService = Context.getInstance(CohortService.class);
        Assert.assertNotNull(cohortService);
    }

    @After
    public void cleanUp() {
        URL lucenePath = AdministrativeServiceTest.class.getResource("../lucene");

        File luceneDirectory = new File(lucenePath.getPath());
        for (String filename : luceneDirectory.list()) {
            File file = new File(luceneDirectory, filename);
            Assert.assertTrue(file.delete());
        }
    }

    /**
     * @verifies return cohort with matching uuid
     * @see CohortService#getCohortByUuid(String)
     */
    @Test
    public void getCohortByUuid_shouldReturnCohortWithMatchingUuid() throws Exception {
        String uuid = "0ca78602-738f-408d-8ced-386ad12367db";
        Cohort cohort = cohortService.getCohortByUuid(uuid);
        Assert.assertNotNull(cohort);
    }

    /**
     * @verifies return null when no cohort match the uuid
     * @see CohortService#getCohortByUuid(String)
     */
    @Test
    public void getCohortByUuid_shouldReturnNullWhenNoCohortMatchTheUuid() throws Exception {
        String randomUuid = "1234";
        Cohort cohort = cohortService.getCohortByUuid(randomUuid);
        Assert.assertNull(cohort);
    }

    /**
     * @verifies return list of all cohorts with matching name
     * @see CohortService#getCohortsByName(String)
     */
    @Test
    public void getCohortsByName_shouldReturnListOfAllCohortsWithMatchingName() throws Exception {
        String name = "Fem";
        List<Cohort> cohorts = cohortService.getCohortsByName(name);
        Assert.assertNotNull(cohorts);
        Assert.assertFalse(cohorts.isEmpty());
    }

    /**
     * @verifies return empty list when no cohort match the name
     * @see CohortService#getCohortsByName(String)
     */
    @Test
    public void getCohortsByName_shouldReturnEmptyListWhenNoCohortMatchTheName() throws Exception {
        String randomName = "RandomName";
        List<Cohort> cohorts = cohortService.getCohortsByName(randomName);
        Assert.assertNotNull(cohorts);
        Assert.assertTrue(cohorts.isEmpty());
    }

    /**
     * @verifies return all registered cohorts
     * @see CohortService#getAllCohorts()
     */
    @Test
    public void getAllCohorts_shouldReturnAllRegisteredCohorts() throws Exception {
        List<Cohort> cohorts = cohortService.getAllCohorts();
        Assert.assertNotNull(cohorts);
        Assert.assertFalse(cohorts.isEmpty());
    }

    /**
     * @verifies return empty list when no cohort is registered
     * @see CohortService#getAllCohorts()
     */
    @Test
    public void getAllCohorts_shouldReturnEmptyListWhenNoCohortIsRegistered() throws Exception {
        List<Cohort> cohorts = cohortService.getAllCohorts();
        Assert.assertNotNull(cohorts);
        Assert.assertFalse(cohorts.isEmpty());

        for (Cohort cohort : cohorts)
            cohortService.deleteCohort(cohort);

        cohorts = cohortService.getAllCohorts();
        Assert.assertNotNull(cohorts);
        Assert.assertTrue(cohorts.isEmpty());
    }
}
