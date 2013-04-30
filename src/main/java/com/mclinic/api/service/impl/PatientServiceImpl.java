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

import com.google.inject.Inject;
import com.mclinic.api.dao.PatientDao;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.PatientService;
import com.mclinic.util.Constants;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

public class PatientServiceImpl implements PatientService {

    @Inject
    private PatientDao patientDao;

    /**
     * Download a single patient record from the patient rest resource into the local lucene repository.
     *
     * @param uuid the uuid of the patient.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download patient with matching uuid.
     */
    @Override
    public void downloadPatientByUuid(final String uuid) throws IOException, ParseException {
        patientDao.download(uuid, Constants.UUID_PATIENT_RESOURCE);
    }

    /**
     * Download all patients with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the patient to be downloaded. When empty, will return all patients available.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download all patient with partially matched name.
     * @should download all patient when name is empty.
     */
    @Override
    public void downloadPatientsByName(final String name) throws IOException, ParseException {
        patientDao.download(name, Constants.SEARCH_PATIENT_RESOURCE);
    }

    /**
     * Get a single patient record from the local repository with matching uuid.
     *
     * @param uuid the patient uuid
     * @return patient with matching uuid or null when no patient match the uuid
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return patient with matching uuid
     * @should return null when no patient match the uuid
     */
    @Override
    public Patient getPatientByUuid(final String uuid) throws IOException, ParseException {
        return patientDao.getByUuid(uuid);
    }

    /**
     * Get patient by the identifier of the patient.
     *
     * @param identifier the patient identifier.
     * @return patient with matching identifier or null when no patient match the identifier.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return patient with matching identifier.
     * @should return null when no patient match the identifier.
     */
    @Override
    public Patient getPatientByIdentifier(final String identifier) throws IOException, ParseException {
        return patientDao.getByIdentifier(identifier);
    }

    /**
     * Get all saved patients in the local repository.
     *
     * @return all registered patients or empty list when no patient is registered.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return all registered patients.
     * @should return empty list when no patient is registered.
     */
    @Override
    public List<Patient> getAllPatients() throws IOException, ParseException {
        return patientDao.getAll();
    }

    /**
     * Get list of patients with name similar to the search term.
     *
     * @param name the patient name.
     * @return list of all patients with matching name or empty list when no patient match the name.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return list of all patients with matching name partially.
     * @should return empty list when no patient match the name.
     */
    @Override
    public List<Patient> getPatientsByName(final String name) throws IOException, ParseException {
        return patientDao.getByName(name);
    }

    /**
     * Search for patients with matching characteristic on the name or identifier with the search term.
     *
     * @param term the search term.
     * @return list of all patients with matching search term on the searchable fields or empty list.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return list of all patients with matching search term on the searchable fields.
     * @should return empty list when no patient match the search term.
     */
    @Override
    public List<Patient> searchPatients(final String term) throws IOException, ParseException {
        return patientDao.search(term);
    }

    /**
     * Delete a single patient object from the local repository.
     *
     * @param patient the patient object.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should delete the patient object from the local repository.
     */
    @Override
    public void deletePatient(final Patient patient) throws IOException, ParseException {
        try {
            patientDao.delete(patient, Constants.UUID_PATIENT_RESOURCE);
        } catch (IOException e) {
            patientDao.delete(patient, Constants.SEARCH_PATIENT_RESOURCE);
        }
    }
}
