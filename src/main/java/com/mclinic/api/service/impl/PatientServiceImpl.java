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

import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.PatientDao;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.PatientService;

public class PatientServiceImpl implements PatientService {

    @Inject
    private PatientDao dao;

    @Override
    public Patient createPatient(final Patient patient) {
        return dao.createPatient(patient);
    }

    @Override
    public Patient updatePatient(final Patient patient) {
        return dao.updatePatient(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return dao.getAllPatients();
    }

    @Override
    public Patient getPatientByIdentifier(final String identifier) {
        return dao.getPatientByIdentifier(identifier);
    }

    @Override
    public void deletePatient(final Patient patient) {
        dao.deletePatient(patient);
    }

    @Override
    public void deleteAllPatients() {
        dao.deleteAllPatients();
    }

    @Override
    public Patient getPatientByUuid(final String uuid) {
        return dao.getPatientByUuid(uuid);
    }

    @Override
    public List<Patient> getPatientsByName(final String name) {
        return dao.getPatientsByName(name);
    }
}
