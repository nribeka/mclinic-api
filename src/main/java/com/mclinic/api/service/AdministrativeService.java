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
     * Service method to initialize the internal lucene repository.
     *
     * @param configurationFile the configuration file to convert json files into lucene objects
     * @should register available algorithm classes
     * @should register available resolver classes
     * @should register available domain object classes
     * @should load configuration file and register them
     */
    void initializeRepository(final File configurationFile);

    /**
     * Load all cohorts in to the lucene index. This method will load all <code>Cohort</code> as represented
     * by all json files in @jsonFiles directory into the lucene search index engine. <br><br>
     * <p/>
     * The method should be called once in the life time of a project. If new json files have been added to
     * the Json directory then update should be the method to call. <br><br>
     *
     * @param jsonFiles the directory containing all cohorts json files.
     * @should load all cohort data from local directory
     */
    void loadCohorts(final File jsonFiles);

    /**
     * Load all patients in to the lucene index. This method will load all <code>Patient</code> as represented
     * by all json files in @jsonFiles directory into the lucene search index engine. <br><br>
     * <p/>
     * The method should be called once in the life time of a project. If new json files have been added to
     * the Json directory then update should be the method to call. <br><br>
     *
     * @param jsonFiles the directory containing all patients json files.
     * @should load all patient data from local directory
     */
    void loadPatients(final File jsonFiles);

    /**
     * Load all observations in to the lucene index. This method will load all <code>Observation</code> as represented
     * by all json files in @dojsonFiles directory into the lucene search index engine. <br><br>
     * <p/>
     * The method should be called once in the life time of a project. If new json files have been added to
     * the Json directory then update should be the method to call. <br><br>
     *
     * @param jsonFiles the directory containing all observations json files.
     * @should load all observations data from local directory
     */
    void loadObservations(final File jsonFiles);

    /**
     * Load all patients in to the lucene index. This method will load all <code>Patient</code> as represented
     * by all json files in @jsonFiles directory into the lucene search index engine. <br><br>
     * <p/>
     * The method should be called once in the life time of a project. If new json files have been added to
     * the Json directory then update should be the method to call. <br><br>
     *
     * @param jsonFiles the directory containing all patients json files.
     * @should load all patient data from local directory
     */
    void loadCohortPatients(final File jsonFiles);

    void downloadCohorts();

    void downloadPatients();

    void downloadObservations(final String patientUuid);

    void downloadCohortPatients(final String cohortUuid);
}