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

import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.search.api.Context;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ObservationServiceTest {

    private PatientService patientService;

    private ObservationService observationService;

    private AdministrativeService service;

    @Before
    public void prepare() throws Exception {
        URL repositoryPath = AdministrativeServiceTest.class.getResource("../j2l");
        URL lucenePath = AdministrativeServiceTest.class.getResource("../lucene");
        Context.initialize(new MuzimaModule(lucenePath.getPath(), "uuid"));

        service = Context.getInstance(AdministrativeService.class);
        Assert.assertNotNull(service);

        service.initializeRepository(repositoryPath.getPath());

        URL patientPath = AdministrativeServiceTest.class.getResource("../json/patient");
        service.loadPatients(new File(patientPath.getPath()));
        URL observationPath = AdministrativeServiceTest.class.getResource("../json/observation");
        service.loadObservations(new File(observationPath.getPath()));

        patientService = Context.getInstance(PatientService.class);
        Assert.assertNotNull(patientService);

        observationService = Context.getInstance(ObservationService.class);
        Assert.assertNotNull(observationService);
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
     * @verifies return observation with matching uuid
     * @see ObservationService#getObservationByUuid(String)
     */
    @Test
    public void getObservationByUuid_shouldReturnObservationWithMatchingUuid() throws Exception {
        String uuid = "d05a50ea-1691-11df-97a5-7038c432aabf";
        Observation observation = observationService.getObservationByUuid(uuid);
        Assert.assertNotNull(observation);
    }

    /**
     * @verifies return null when no observation match the uuid
     * @see ObservationService#getObservationByUuid(String)
     */
    @Test
    public void getObservationByUuid_shouldReturnNullWhenNoObservationMatchTheUuid() throws Exception {
        String uuid = "1234";
        Observation observation = observationService.getObservationByUuid(uuid);
        Assert.assertNull(observation);
    }

    /**
     * @verifies return list of all observations for the patient
     * @see ObservationService#getAllObservations(com.mclinic.api.model.Patient)
     */
    @Test
    public void getAllObservations_shouldReturnListOfAllObservationsForThePatient() throws Exception {
        String patientUuid = "dd55e586-1691-11df-97a5-7038c432aabf";
        Patient patient = patientService.getPatientByUuid(patientUuid);
        Assert.assertNotNull(patient);
        List<Observation> observations = observationService.getAllObservations(patient);
        Assert.assertNotNull(observations);
        Assert.assertFalse(observations.isEmpty());
    }

    /**
     * @verifies return empty list when no observation found for the patient
     * @see ObservationService#getAllObservations(com.mclinic.api.model.Patient)
     */
    @Test
    public void getAllObservations_shouldReturnEmptyListWhenNoObservationFoundForThePatient() throws Exception {
        // patient uuid with no observation data
        String patientUuid = "dda926e4-1691-11df-97a5-7038c432aabf";
        Patient patient = patientService.getPatientByUuid(patientUuid);
        Assert.assertNotNull(patient);
        List<Observation> observations = observationService.getAllObservations(patient);
        Assert.assertNotNull(observations);
        Assert.assertTrue(observations.isEmpty());
    }
}
