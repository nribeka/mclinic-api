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
package com.mclinic.api.service.impl;

import java.io.File;

import com.google.inject.Inject;
import com.mclinic.api.dao.AdministrativeDao;
import com.mclinic.api.service.AdministrativeService;

public class AdministrativeServiceImpl implements AdministrativeService {

    @Inject
    private AdministrativeDao dao;

    @Override
    public void initializeRepository(final String repositoryPath) {
        dao.initializeRepository(repositoryPath);
    }

    @Override
    public void initializeRepository(final File repositoryDir) {
        dao.initializeRepository(repositoryDir);
    }

    @Override
    public void loadForms(final File jsonFiles) {
        dao.loadForms(jsonFiles);
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
    public void loadUsers(final File jsonFiles) {
        dao.loadUsers(jsonFiles);
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
    public void downloadForms() {
        dao.downloadForms();
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
    public void downloadUsers(final String username) {
        dao.downloadUsers(username);
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
