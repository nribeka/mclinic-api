package com.mclinic.api.service.impl;

import java.io.File;

import com.google.inject.Inject;
import com.mclinic.api.dao.AdministrativeDao;
import com.mclinic.api.service.AdministrativeService;

public class AdministrativeServiceImpl implements AdministrativeService {

    @Inject
    private AdministrativeDao dao;

    @Override
    public void initializeRepository(final File configurationFile) {
        dao.initializeRepository(configurationFile);
    }

    @Override
    public void loadCohorts(final File jsonFiles) {
        dao.loadCohorts(jsonFiles);
    }

    @Override
    public void loadPatients(final File jsonFiles) {
        dao.loadPatients(jsonFiles);
    }

    @Override
    public void loadObservations(final File jsonFiles) {
        dao.loadObservations(jsonFiles);
    }

    @Override
    public void loadCohortPatients(final File jsonFiles) {
        dao.loadCohortPatients(jsonFiles);
    }

    @Override
    public void downloadCohorts() {
        dao.downloadCohorts();
    }

    @Override
    public void downloadPatients() {
        dao.downloadPatients();
    }

    @Override
    public void downloadCohortPatients(final String cohortUuid) {
        dao.downloadCohortPatients(cohortUuid);
    }

    @Override
    public void downloadObservations(final String patientUuid) {
        dao.downloadObservations(patientUuid);
    }
}