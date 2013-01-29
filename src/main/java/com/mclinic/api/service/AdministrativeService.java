/**
 * Copyright 2012 Muzima Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
     * @should register available algorithm classes
     * @should register available resolver classes
     * @should register available domain object classes
     * @should load configuration file and register them
     */
    void initializeRepository(final String repositoryPath);

    void initializeRepository(final File repositoryDir);

    /**
     * Load all forms in to the lucene index. This method will load all <code>Form</code> as represented
     * by all json files in @jsonFiles directory into the lucene search index engine. <br><br>
     * <p/>
     * The method should be called once in the life time of a project. If new json files have been added to
     * the Json directory then updateForm should be the method to call. <br><br>
     *
     * @param jsonFiles the directory containing all forms json files.
     * @should load all form data from local directory
     */
    void loadForms(final File jsonFiles);

    /**
     * Load all cohorts in to the lucene index. This method will load all <code>Cohort</code> as represented
     * by all json files in @jsonFiles directory into the lucene search index engine. <br><br>
     * <p/>
     * The method should be called once in the life time of a project. If new json files have been added to
     * the Json directory then updateCohort should be the method to call. <br><br>
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
     * the Json directory then updatePatient should be the method to call. <br><br>
     *
     * @param jsonFiles the directory containing all patients json files.
     * @should load all patient data from local directory
     */
    void loadPatients(final File jsonFiles);

    /**
     * Load all users in to the lucene index. This method will load all <code>User</code> as represented
     * by all json files in @jsonFiles directory into the lucene search index engine. <br><br>
     * <p/>
     * The method should be called once in the life time of a project. If new json files have been added to
     * the Json directory then updateUser should be the method to call. <br><br>
     *
     * @param jsonFiles the directory containing all users json files.
     * @should load all user data from local directory
     */

    void loadUsers(final File jsonFiles);

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

    void downloadForms();

    void downloadCohorts();

    void downloadPatients();

    void downloadUsers(final String username);

    void downloadObservations(final String patientUuid);

    void downloadCohortPatients(final String cohortUuid);
}
