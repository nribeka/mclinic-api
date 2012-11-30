package com.mclinic.api;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.RestAssuredService;
import com.burkeware.search.api.logger.Logger;
import com.burkeware.search.api.resource.Resource;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.model.Patient;
import com.mclinic.api.model.algorithm.CohortAlgorithm;
import com.mclinic.api.model.algorithm.CohortMemberAlgorithm;
import com.mclinic.api.model.algorithm.PatientAlgorithm;
import com.mclinic.api.model.resolver.CohortMemberResolver;
import com.mclinic.api.model.resolver.CohortResolver;
import com.mclinic.api.model.resolver.PatientResolver;
import com.mclinic.api.module.MclinicAPIModule;
import com.mclinic.api.service.PatientService;

public class CohortMemberServiceTest {
	
	static RestAssuredService	service; 
	
	static Logger log;
	
    @SuppressWarnings("unchecked")
    @Test
    public void initializeDB_shouldRegisterAllItems() throws Exception {
        URL j2l = CohortMemberServiceTest.class.getResource("j2l");
        URL lucene = CohortMemberServiceTest.class.getResource("lucene");
        
        MclinicAPIModule module = new MclinicAPIModule(lucene.getPath(), "uuid");
        module.setServer("http://localhost:8080/openmrs/");
        module.setUsername("admin");
        module.setPassword("test");
        Context.initialize(module);
        
		try {
			Context.registerObject(Patient.class, Cohort.class);
			Context.registerAlgorithm(PatientAlgorithm.class, CohortAlgorithm.class, CohortMemberAlgorithm.class);
			Context.registerResolver(PatientResolver.class, CohortResolver.class, CohortMemberResolver.class);
			Context.registerResources(new File(j2l.getPath()));
		} catch (Exception e) {
			log.error("CohortMemberServiceTest", "Error initializing");
		}

        Resource resource = Context.getResource("Patient");
        Assert.assertNotNull(resource);
        
        resource = Context.getResource("Cohort");
        Assert.assertNotNull(resource);
        
        resource = Context.getResource("Cohort Member");
        Assert.assertNotNull(resource);
        
        service= Context.getService();;
    }
    
    @Test
	public void loadCohortMembers_shouldLoadCohortMembersFromJsonFiles() {
    	URL cohortMemberJsons = CohortMemberServiceTest.class.getResource("json/cohort_member");
    	
 		try {
 			service.loadObjects("*", Context.getResource("Cohort Member"), new File(cohortMemberJsons.getPath()));
 			Assert.assertNotNull(service.getObjects(null, Patient.class));
 		} catch (Exception e) {
 			log.error("CohortMemberServiceTest", "Error loading members");
 		}
	}
    
    @Test
	public void getAllPatients_shouldReturnAllPatientsInDB() {
	    PatientService pService = Context.getInstance(PatientService.class);
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
}