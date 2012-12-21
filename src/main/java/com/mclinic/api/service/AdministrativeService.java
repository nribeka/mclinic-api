package com.mclinic.api.service;

import java.io.File;

import com.google.inject.ImplementedBy;
import com.mclinic.api.service.impl.AdministrativeServiceImpl;


/**
 * Service handling all administrative operations
 */
@ImplementedBy(AdministrativeServiceImpl.class)
public interface AdministrativeService {

    /**
     * @param configurationFile the j2l file to use to convert json files into lucene objects
     */
    void initializeDB(final File configurationFile);

    /**
     * Load all patients in to the lucene index. This method will load all <code>Patient</code> as represented
     * by all json files in @jsonFiles directory into the lucene search index engine. <br><br>
     * <p/>
     * The method should be called once in the life time of a project. If new json files have been added to
     * the Json directory then update should be the method to call. <br><br>
     *
     * @param jsonFiles the directory containing all patients json files.
     */
    void loadPatients(final File jsonFiles);

    void loadCohorts(final File jsonFiles);

    void downloadCohorts();

    void loadCohortPatients(final File jsonFiles);

    void downloadCohortPatients(final String cohortUuid);

    void loadObservations(final File jsonFiles);

    void downloadObservations(final String patientUuid);
}