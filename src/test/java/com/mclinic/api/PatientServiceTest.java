package com.mclinic.api;

import java.io.File;
import java.net.URL;
import java.util.List;

import com.mclinic.api.model.Cohort;
import com.mclinic.api.model.Patient;
import com.mclinic.api.model.algorithm.CohortAlgorithm;
import com.mclinic.api.model.algorithm.CohortMemberAlgorithm;
import com.mclinic.api.model.algorithm.PatientAlgorithm;
import com.mclinic.api.model.resolver.CohortMemberResolver;
import com.mclinic.api.model.resolver.CohortResolver;
import com.mclinic.api.model.resolver.PatientResolver;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.api.service.PatientService;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.resource.Resource;
import junit.framework.Assert;
import org.junit.Test;

public class PatientServiceTest {

    static RestAssuredService service;

    static Logger log;

    static PatientService pService;

    /**
     * This is actually an {@PatientServiceTest} method it should be a copy
     * paste from there and actual test is in that Test class
     */
    @SuppressWarnings("unchecked")
    @Test
    public void initializeDB_shouldRegisterAllItems() throws Exception {
        URL j2l = PatientServiceTest.class.getResource("j2l");
        URL lucene = PatientServiceTest.class.getResource("lucene");

        MuzimaModule module = new MuzimaModule(lucene.getPath(), "uuid");
        module.setServer("http://localhost:8080/openmrs/");
        module.setUsername("admin");
        module.setPassword("test");
        Context.initialize(module);

        try {
            Context.registerObject(Cohort.class);
            Context.registerObject(Patient.class);

            Context.registerAlgorithm(PatientAlgorithm.class);
            Context.registerAlgorithm(CohortAlgorithm.class);
            Context.registerAlgorithm(CohortMemberAlgorithm.class);

            Context.registerResolver(PatientResolver.class);
            Context.registerResolver(CohortResolver.class);
            Context.registerResolver(CohortMemberResolver.class);

            Context.registerResources(new File(j2l.getPath()));
        } catch (Exception e) {
            log.error("PatientServiceTest", "Error initializing");
        }

        Resource resource = Context.getResource("Patient");
        Assert.assertNotNull(resource);

        resource = Context.getResource("Cohort");
        Assert.assertNotNull(resource);

        resource = Context.getResource("Cohort Member");
        Assert.assertNotNull(resource);

        service = Context.getService();
    }

    /**
     * This is actually an {@PatientServiceTest} method it should be a copy
     * paste from there and actual test is in that Test class
     */
    @Test
    public void loadPatients_shouldLoadPatientsFromJsonFiles() {
        URL patientJsons = PatientServiceTest.class.getResource("json/patient");

        try {
            service.loadObjects("*", Context.getResource("Patient"), new File(patientJsons.getPath()));
            Assert.assertNotNull(service.getObjects(null, Patient.class));
        } catch (Exception e) {
            log.error("PatientServiceTest", "Error loading patients from json");
        }
    }

    @Test
    public void getAllPatients_shouldReturnAllPatientsInDB() {
        pService = Context.getInstance(PatientService.class);
        System.out.println("ALL PATIENTS");
        List<Patient> pats = pService.getAllPatients();
        Assert.assertNotNull(pats);
        if (pats != null && pats.size() > 0) {
            for (Patient pat : pats) {
                System.out.print("[" + pat.getUuid() + "]");
                System.out.print("-[" + pat.getName() + "]");
                System.out.print("-[" + pat.getIdentifier() + "]");
                System.out.print("-[" + pat.getGender() + "]");
                System.out.print("-[" + pat.getBirthdate() + "]");
                System.out.println();
            }
        } else
            System.out.println("\nNO PATIENTS");
    }

    @Test
    public void getPatientsByName_shouldReturnPatientsListGivenName() {
        System.out.println("PATIENTS BY NAME");
        List<Patient> pats = pService.getPatientsByName("Testarius");
        Assert.assertNotNull(pats);
        if (pats != null && pats.size() > 0) {
            for (Patient pat : pats) {
                System.out.print("[" + pat.getUuid() + "]");
                System.out.print("-[" + pat.getName() + "]");
                System.out.print("-[" + pat.getIdentifier() + "]");
                System.out.print("-[" + pat.getGender() + "]");
                System.out.print("-[" + pat.getBirthdate() + "]");
                System.out.println();
            }
        } else
            System.out.println("Patients is null");
    }

    @Test
    public void getPatientByIdentifier_shouldReturnOnePatient() {
        System.out.println("PATIENT_RESOURCE BY IDENTIFIER");
        Patient pat = pService.getPatientByIdentifier("363MO-5");
        Assert.assertNotNull(pat);
        if (pat != null) {
            System.out.print("[" + pat.getUuid() + "]");
            System.out.print("-[" + pat.getName() + "]");
            System.out.print("-[" + pat.getIdentifier() + "]");
            System.out.print("-[" + pat.getGender() + "]");
            System.out.print("-[" + pat.getBirthdate() + "]");
        } else {
            System.out.println("Patient is null");
        }
        System.out.println();
    }

    @Test
    public void getPatientByUUID_shouldReturnOnePatient() {
        System.out.println("\nPATIENT_RESOURCE BY UUID");
        Patient pat = pService.getPatientByUuid("dd55e586-1693-11df-97a5-7038c432aabf");
        Assert.assertNotNull(pat);
        if (pat != null) {
            System.out.print("[" + pat.getUuid() + "]");
            System.out.print("-[" + pat.getName() + "]");
            System.out.print("-[" + pat.getIdentifier() + "]");
            System.out.print("-[" + pat.getGender() + "]");
            System.out.print("-[" + pat.getBirthdate() + "]");
        } else {
            System.out.println("Patient is null");
        }
        System.out.println();
    }

    @Test
    public void deletePatient_shouldDeleteOnePatient() {
        System.out.println("\nDELETE PATIENT_RESOURCE");
        Patient pat = pService.getPatientByUuid("dd55e586-1693-11df-97a5-7038c432aabf");
        Assert.assertNotNull(pat);
        pService.deletePatient(pat);
        getAllPatients_shouldReturnAllPatientsInDB();
    }
}