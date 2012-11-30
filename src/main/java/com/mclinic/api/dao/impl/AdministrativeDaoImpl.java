package com.mclinic.api.dao.impl;

import java.io.File;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.RestAssuredService;
import com.burkeware.search.api.logger.Logger;
import com.google.inject.Inject;
import com.mclinic.api.dao.AdministrativeDao;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.api.model.algorithm.CohortAlgorithm;
import com.mclinic.api.model.algorithm.CohortMemberAlgorithm;
import com.mclinic.api.model.algorithm.ObservationAlgorithm;
import com.mclinic.api.model.algorithm.PatientAlgorithm;
import com.mclinic.api.model.resolver.CohortMemberResolver;
import com.mclinic.api.model.resolver.CohortResolver;
import com.mclinic.api.model.resolver.ObservationResolver;
import com.mclinic.api.model.resolver.PatientResolver;
import com.mclinic.util.Constants;

@SuppressWarnings("unchecked")
public class AdministrativeDaoImpl implements AdministrativeDao {
	
	@Inject
	private RestAssuredService	service;
	
	@Inject
	private Logger log;
	
	private String TAG = "AdministrativeDao"; 
	
	@Override
	public void initializeDB(File j2lFile) {
		try {
			Context.registerObject(Patient.class, Cohort.class, Observation.class);
			
			Context.registerAlgorithm(PatientAlgorithm.class, 
												CohortAlgorithm.class, 
												CohortMemberAlgorithm.class,
												ObservationAlgorithm.class);
			
			Context.registerResolver( PatientResolver.class,
												CohortResolver.class, 
												CohortMemberResolver.class,
												ObservationResolver.class);

			Context.registerResources(j2lFile);
			
		} catch (Exception e) {
			log.debug(TAG, "Error initializing Database " + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void loadPatients(File jsonFilesDir) {
		try {
			service.loadObjects("*", Context.getResource(Constants.PATIENT), jsonFilesDir);
		} catch (Exception e) {
			log.debug(TAG, "Error loading patients from local json file "
					+ jsonFilesDir.getAbsolutePath() + "\n" + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void downloadPatients() {
		try {
			service.loadObjects("*", Context.getResource(Constants.PATIENT));
		} catch (Exception e) {
			log.debug(TAG, "Error downloading patients from URL " + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void loadObservations(File jsonFilesDir) {
		try {
			service.loadObjects("*", Context.getResource(Constants.OBSERVATION), jsonFilesDir);
		} catch (Exception e) {
			log.debug(TAG, "Error loading observations from local json file "
					+ jsonFilesDir.getAbsolutePath() + "\n" + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void downloadObservations(String patientUUID) {
		try {
			service.loadObjects(patientUUID, Context.getResource(Constants.OBSERVATION));
		} catch (Exception e) {
			log.debug(TAG, "Error downloading observations from URL " + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void loadCohorts(File jsonFilesDir) {
		try {
			service.loadObjects("*", Context.getResource(Constants.COHORT), jsonFilesDir);
		} catch (Exception e) {
			log.debug(TAG, "Error loading cohorts from local json file " + jsonFilesDir.getAbsolutePath()
					+ "\n" + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void downloadCohorts() {
		try {
			service.loadObjects(null, Context.getResource(Constants.COHORT));
		} catch (Exception e) {
			log.debug(TAG, "Error downloading cohorts from URL " + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void loadCohortPatients(File jsonFilesDir) {
		try {
			service.loadObjects("*", Context.getResource(Constants.COHORT_MEMBER), jsonFilesDir);
		} catch (Exception e) {
			log.debug(TAG, "Error loading cohort members from local json file " + jsonFilesDir.getAbsolutePath()
					+ "\n" + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void downloadCohortPatients(String cohortUUID) {
		try {
			service.loadObjects(cohortUUID, Context.getResource("Cohort Member"));
		} catch (Exception e) {
			log.debug(TAG, "Error downloading cohort members for cohort = " + cohortUUID + " from URL " + e.getLocalizedMessage());
		}
	}
}