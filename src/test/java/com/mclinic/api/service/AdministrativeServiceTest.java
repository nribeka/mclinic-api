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
package com.mclinic.api.service;

import com.mclinic.api.model.Cohort;
import com.mclinic.api.model.Form;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.api.model.algorithm.CohortAlgorithm;
import com.mclinic.api.model.algorithm.MemberAlgorithm;
import com.mclinic.api.model.algorithm.ObservationAlgorithm;
import com.mclinic.api.model.algorithm.PatientAlgorithm;
import com.mclinic.api.model.resolver.MemberCohortResolver;
import com.mclinic.api.model.resolver.SearchCohortResolver;
import com.mclinic.api.model.resolver.SearchObservationResolver;
import com.mclinic.api.model.resolver.SearchPatientResolver;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.resolver.Resolver;
import com.mclinic.search.api.resource.Resource;
import com.mclinic.search.api.serialization.Algorithm;
import com.mclinic.search.api.util.StringUtil;
import com.mclinic.util.Constants;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class AdministrativeServiceTest {

    private AdministrativeService service;

    private RestAssuredService assuredService;

    @Before
    public void prepare() throws Exception {
        URL repositoryPath = AdministrativeServiceTest.class.getResource("../j2l");
        URL lucenePath = AdministrativeServiceTest.class.getResource("../lucene");
        Context.initialize(new MuzimaModule(lucenePath.getPath(), "uuid"));

        service = Context.getInstance(AdministrativeService.class);
        Assert.assertNotNull(service);

        assuredService = Context.getInstance(RestAssuredService.class);
        Assert.assertNotNull(assuredService);

        service.initializeRepository(repositoryPath.getPath());
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
     * @verifies register available algorithm classes
     * @see AdministrativeService#initializeRepository(String)
     */
    @Test
    public void initializeRepository_shouldRegisterAvailableAlgorithmClasses() throws Exception {
        Class<? extends Algorithm> algorithmClass;

        algorithmClass = Context.getAlgorithm(CohortAlgorithm.class);
        Assert.assertNotNull(algorithmClass);

        algorithmClass = Context.getAlgorithm(MemberAlgorithm.class);
        Assert.assertNotNull(algorithmClass);

        algorithmClass = Context.getAlgorithm(ObservationAlgorithm.class);
        Assert.assertNotNull(algorithmClass);

        algorithmClass = Context.getAlgorithm(PatientAlgorithm.class);
        Assert.assertNotNull(algorithmClass);
    }

    /**
     * @verifies register available resolver classes
     * @see AdministrativeService#initializeRepository(String)
     */
    @Test
    public void initializeRepository_shouldRegisterAvailableResolverClasses() throws Exception {
        Class<? extends Resolver> resolverClass;

        resolverClass = Context.getResolver(SearchCohortResolver.class);
        Assert.assertNotNull(resolverClass);

        resolverClass = Context.getResolver(MemberCohortResolver.class);
        Assert.assertNotNull(resolverClass);

        resolverClass = Context.getResolver(SearchObservationResolver.class);
        Assert.assertNotNull(resolverClass);

        resolverClass = Context.getResolver(SearchPatientResolver.class);
        Assert.assertNotNull(resolverClass);
    }

    /**
     * @verifies register available domain object classes
     * @see AdministrativeService#initializeRepository(String)
     */
    @Test
    public void initializeRepository_shouldRegisterAvailableDomainObjectClasses() throws Exception {
        Class<? extends Object> domainClass;

        domainClass = Context.removeObject(Cohort.class);
        Assert.assertNotNull(domainClass);

        domainClass = Context.removeObject(Observation.class);
        Assert.assertNotNull(domainClass);

        domainClass = Context.removeObject(Patient.class);
        Assert.assertNotNull(domainClass);
    }

    /**
     * @verifies load configuration file and register them
     * @see AdministrativeService#initializeRepository(String)
     */
    @Test
    public void initializeRepository_shouldLoadConfigurationFileAndRegisterThem() throws Exception {
        Resource resource;

        resource = Context.getResource(Constants.PATIENT_RESOURCE);
        Assert.assertNotNull(resource);

        resource = Context.getResource(Constants.COHORT_MEMBER_RESOURCE);
        Assert.assertNotNull(resource);

        resource = Context.getResource(Constants.SEARCH_COHORT_RESOURCE);
        Assert.assertNotNull(resource);

        resource = Context.getResource(Constants.OBSERVATION_RESOURCE);
        Assert.assertNotNull(resource);
    }

    /**
     * @verifies load all form data from local directory
     * @see AdministrativeService#loadForms(java.io.File)
     */
    @Test
    public void loadCohorts_shouldLoadAllFormDataFromLocalDirectory() throws Exception {
        URL jsonPath = AdministrativeServiceTest.class.getResource("../json/form");
        service.loadForms(new File(jsonPath.getPath()));

        List<Form> forms = assuredService.getObjects(StringUtil.EMPTY, Form.class);
        Assert.assertNotNull(forms);
        Assert.assertFalse(forms.isEmpty());
    }

    /**
     * @verifies load all cohort data from local directory
     * @see AdministrativeService#loadCohorts(java.io.File)
     */
    @Test
    public void loadCohorts_shouldLoadAllCohortDataFromLocalDirectory() throws Exception {
        URL jsonPath = AdministrativeServiceTest.class.getResource("../json/cohort");
        service.loadCohorts(new File(jsonPath.getPath()));

        List<Cohort> cohorts = assuredService.getObjects(StringUtil.EMPTY, Cohort.class);
        Assert.assertNotNull(cohorts);
        Assert.assertFalse(cohorts.isEmpty());
    }

    /**
     * @verifies load all patient data from local directory
     * @see AdministrativeService#loadPatients(java.io.File)
     */
    @Test
    public void loadPatients_shouldLoadAllPatientDataFromLocalDirectory() throws Exception {
        URL jsonPath = AdministrativeServiceTest.class.getResource("../json/patient");
        service.loadPatients(new File(jsonPath.getPath()));

        List<Patient> patients = assuredService.getObjects(StringUtil.EMPTY, Patient.class);
        Assert.assertNotNull(patients);
        Assert.assertFalse(patients.isEmpty());
    }

    /**
     * @verifies load all observations data from local directory
     * @see AdministrativeService#loadObservations(java.io.File)
     */
    @Test
    public void loadObservations_shouldLoadAllObservationsDataFromLocalDirectory() throws Exception {
        URL jsonPath = AdministrativeServiceTest.class.getResource("../json/observation");
        service.loadObservations(new File(jsonPath.getPath()));

        List<Observation> observations = assuredService.getObjects(StringUtil.EMPTY, Observation.class);
        Assert.assertNotNull(observations);
        Assert.assertFalse(observations.isEmpty());
    }

    /**
     * @verifies load all patient data from local directory
     * @see AdministrativeService#loadCohortPatients(java.io.File)
     */
    @Test
    public void loadCohortPatients_shouldLoadAllPatientDataFromLocalDirectory() throws Exception {
        URL jsonPath = AdministrativeServiceTest.class.getResource("../json/cohort_member");
        service.loadCohortPatients(new File(jsonPath.getPath()));

        List<Patient> patients = assuredService.getObjects(StringUtil.EMPTY, Patient.class);
        Assert.assertNotNull(patients);
        Assert.assertFalse(patients.isEmpty());
    }
}
