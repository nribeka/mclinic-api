package com.mclinic.api.dao.impl;

import java.io.File;

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
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.resource.Resource;
import com.mclinic.search.api.util.StringUtil;
import com.mclinic.util.Constants;

public class AdministrativeDaoImpl implements AdministrativeDao {

    @Inject
    private Logger log;

    @Inject
    private RestAssuredService service;

    private static final String TAG = AdministrativeDao.class.getSimpleName();

    @Override
    public void initializeDB(final File configurationFile) {
        try {
            Context.registerObject(Patient.class);
            Context.registerObject(Cohort.class);
            Context.registerObject(Observation.class);

            Context.registerAlgorithm(PatientAlgorithm.class);
            Context.registerAlgorithm(CohortAlgorithm.class);
            Context.registerAlgorithm(CohortMemberAlgorithm.class);
            Context.registerAlgorithm(ObservationAlgorithm.class);

            Context.registerResolver(PatientResolver.class);
            Context.registerResolver(CohortResolver.class);
            Context.registerResolver(CohortMemberResolver.class);
            Context.registerResolver(ObservationResolver.class);

            Context.registerResources(configurationFile);

        } catch (Exception e) {
            log.error(TAG, "Error initializing database.", e);
        }
    }

    @Override
    public void loadPatients(final File jsonFiles) {
        try {
            Resource resource = Context.getResource(Constants.PATIENT_RESOURCE);
            service.loadObjects(StringUtil.EMPTY, resource, jsonFiles);
        } catch (Exception e) {
            log.error(TAG, "Error loading patients from local json file " + jsonFiles.getAbsolutePath(), e);
        }
    }

    @Override
    public void downloadPatients() {
        try {
            Resource resource = Context.getResource(Constants.PATIENT_RESOURCE);
            service.loadObjects(StringUtil.EMPTY, resource);
        } catch (Exception e) {
            log.error(TAG, "Error downloading patients from remote REST resource.", e);
        }
    }

    @Override
    public void loadObservations(final File jsonFiles) {
        try {
            Resource resource = Context.getResource(Constants.OBSERVATION_RESOURCE);
            service.loadObjects(StringUtil.EMPTY, resource, jsonFiles);
        } catch (Exception e) {
            log.error(TAG, "Error loading observations from local json file " + jsonFiles.getAbsolutePath(), e);
        }
    }

    @Override
    public void downloadObservations(final String patientUuid) {
        try {
            Resource resource = Context.getResource(Constants.OBSERVATION_RESOURCE);
            service.loadObjects(patientUuid, resource);
        } catch (Exception e) {
            log.error(TAG, "Error downloading observations for patient with uuid: " + patientUuid, e);
        }
    }

    @Override
    public void loadCohorts(final File jsonFiles) {
        try {
            Resource resource = Context.getResource(Constants.COHORT_RESOURCE);
            service.loadObjects(StringUtil.EMPTY, resource, jsonFiles);
        } catch (Exception e) {
            log.error(TAG, "Error loading cohorts from local json file " + jsonFiles.getAbsolutePath(), e);
        }
    }

    @Override
    public void downloadCohorts() {
        try {
            Resource resource = Context.getResource(Constants.COHORT_RESOURCE);
            service.loadObjects(StringUtil.EMPTY, resource);
        } catch (Exception e) {
            log.error(TAG, "Error downloading cohorts from remote REST resource.", e);
        }
    }

    @Override
    public void loadCohortPatients(final File jsonFiles) {
        try {
            Resource resource = Context.getResource(Constants.COHORT_MEMBER_RESOURCE);
            service.loadObjects(StringUtil.EMPTY, resource, jsonFiles);
        } catch (Exception e) {
            log.error(TAG, "Error loading cohort members from local json file " + jsonFiles.getAbsolutePath(), e);
        }
    }

    @Override
    public void downloadCohortPatients(final String cohortUuid) {
        try {
            Resource resource = Context.getResource(Constants.COHORT_MEMBER_RESOURCE);
            service.loadObjects(cohortUuid, resource);
        } catch (Exception e) {
            log.error(TAG, "Error downloading cohort members for cohort = " + cohortUuid, e);
        }
    }
}