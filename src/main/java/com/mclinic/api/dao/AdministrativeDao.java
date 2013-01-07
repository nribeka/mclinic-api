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
package com.mclinic.api.dao;

import java.io.File;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.AdministrativeDaoImpl;

@ImplementedBy(AdministrativeDaoImpl.class)
public interface AdministrativeDao {

    void initializeRepository(final String repositoryPath);

    void initializeRepository(final File repositoryDir);

    void loadCohorts(final File jsonFiles);

    void loadPatients(final File jsonFiles);

    void loadObservations(final File jsonFiles);

    void loadCohortPatients(final File jsonFiles);

    void downloadCohorts();

    void downloadPatients();

    void downloadObservations(final String patientUuid);

    void downloadCohortPatients(final String cohortUuid);
}