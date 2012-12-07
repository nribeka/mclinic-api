package com.mclinic.api.dao;

import java.io.File;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.AdministrativeDaoImpl;

@ImplementedBy(AdministrativeDaoImpl.class)
public interface AdministrativeDao {

    void initializeDB(File j2lFile);

    void loadPatients(File jsonFilesDir);

    void downloadPatients();

    void loadCohorts(File jsonFilesDir);

    void downloadCohorts();

    void loadCohortPatients(File jsonFilesDir);

    void downloadCohortPatients(String cohortUUID);

    void loadObservations(File jsonFilesDir);

    void downloadObservations(String patientUUID);
}