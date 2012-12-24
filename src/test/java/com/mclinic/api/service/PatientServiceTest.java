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

import com.mclinic.api.model.Patient;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.search.api.Context;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PatientServiceTest {

    private PatientService patientService;

    @Before
    public void prepare() throws Exception {
        URL configuration = AdministrativeServiceTest.class.getResource("../j2l");
        URL lucenePath = AdministrativeServiceTest.class.getResource("../lucene");
        Context.initialize(new MuzimaModule(lucenePath.getPath(), "uuid"));

        AdministrativeService service = Context.getInstance(AdministrativeService.class);
        Assert.assertNotNull(service);

        patientService = Context.getInstance(PatientService.class);
        Assert.assertNotNull(patientService);

        service.initializeRepository(new File(configuration.getPath()));
        URL jsonPath = AdministrativeServiceTest.class.getResource("../json/patient");
        service.loadPatients(new File(jsonPath.getPath()));
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
     * @verifies return patient with matching uuid
     * @see PatientService#getPatientByUuid(String)
     */
    @Test
    public void getPatientByUuid_shouldReturnPatientWithMatchingUuid() throws Exception {
        String uuid = "dd55e586-1691-11df-97a5-7038c432aabf";
        Patient patient = patientService.getPatientByUuid(uuid);
        Assert.assertNotNull(patient);
    }

    /**
     * @verifies return null when no patient match the uuid
     * @see PatientService#getPatientByUuid(String)
     */
    @Test
    public void getPatientByUuid_shouldReturnNullWhenNoPatientMatchTheUuid() throws Exception {
        String randomUuid = "1234";
        Patient patient = patientService.getPatientByUuid(randomUuid);
        Assert.assertNull(patient);
    }

    /**
     * @verifies return patient with matching identifier
     * @see PatientService#getPatientByIdentifier(String)
     */
    @Test
    public void getPatientByIdentifier_shouldReturnPatientWithMatchingIdentifier() throws Exception {
        String identifier = "363MO-5";
        Patient patient = patientService.getPatientByIdentifier(identifier);
        Assert.assertNotNull(patient);
    }

    /**
     * @verifies return null when no patient match the identifier
     * @see PatientService#getPatientByIdentifier(String)
     */
    @Test
    public void getPatientByIdentifier_shouldReturnNullWhenNoPatientMatchTheIdentifier() throws Exception {
        String randomIdentifier = "999KT-3";
        Patient patient = patientService.getPatientByIdentifier(randomIdentifier);
        Assert.assertNull(patient);
    }

    /**
     * @verifies return all registered patients
     * @see PatientService#getAllPatients()
     */
    @Test
    public void getAllPatients_shouldReturnAllRegisteredPatients() throws Exception {
        List<Patient> patients = patientService.getAllPatients();
        Assert.assertNotNull(patients);
        Assert.assertFalse(patients.isEmpty());
    }

    /**
     * @verifies return empty list when no patient is registered
     * @see PatientService#getAllPatients()
     */
    @Test
    public void getAllPatients_shouldReturnEmptyListWhenNoPatientIsRegistered() throws Exception {
        List<Patient> patients = patientService.getAllPatients();
        Assert.assertNotNull(patients);
        Assert.assertFalse(patients.isEmpty());

        for (Patient patient : patients)
            patientService.deletePatient(patient);

        patients = patientService.getAllPatients();
        Assert.assertNotNull(patients);
        Assert.assertTrue(patients.isEmpty());
    }

    /**
     * @verifies return list of all patients with matching name
     * @see PatientService#getPatientsByName(String)
     */
    @Test
    public void getPatientsByName_shouldReturnListOfAllPatientsWithMatchingName() throws Exception {
        String name = "Test";
        List<Patient> patients = patientService.getPatientsByName(name);
        Assert.assertNotNull(patients);
        Assert.assertFalse(patients.isEmpty());
    }

    /**
     * @verifies return empty list when no patient match the name
     * @see PatientService#getPatientsByName(String)
     */
    @Test
    public void getPatientsByName_shouldReturnEmptyListWhenNoPatientMatchTheName() throws Exception {
        String name = "RandomName";
        List<Patient> patients = patientService.getPatientsByName(name);
        Assert.assertNotNull(patients);
        Assert.assertTrue(patients.isEmpty());
    }
}
