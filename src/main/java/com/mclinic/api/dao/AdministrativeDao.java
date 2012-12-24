package com.mclinic.api.dao;

import java.io.File;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.AdministrativeDaoImpl;

@ImplementedBy(AdministrativeDaoImpl.class)
public interface AdministrativeDao {

    void initializeRepository(final File configurationFile);

    void loadCohorts(final File jsonFiles);

    void loadPatients(final File jsonFiles);

    void loadObservations(final File jsonFiles);

    void loadCohortPatients(final File jsonFiles);

    void downloadCohorts();

    void downloadPatients();

    void downloadObservations(final String patientUuid);

    void downloadCohortPatients(final String cohortUuid);
}