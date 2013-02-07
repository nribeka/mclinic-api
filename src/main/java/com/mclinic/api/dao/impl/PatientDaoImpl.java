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
package com.mclinic.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.dao.PatientDao;
import com.mclinic.api.model.Patient;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.resource.Resource;
import com.mclinic.search.api.resource.SearchableField;
import com.mclinic.search.api.util.StringUtil;
import com.mclinic.util.Constants;

public class PatientDaoImpl implements PatientDao {

    @Inject
    private Logger log;

    @Inject
    private RestAssuredService service;

    private static final String TAG = PatientDao.class.getSimpleName();

    @Override
    public Patient createPatient(final Patient patient) {
        Object object = null;
        try {
            // by default all locally created patient record will use the patient resource
            // because it's a single patient record and not necessarily part of a certain cohort
            Resource resource = Context.getResource(Constants.PATIENT_RESOURCE);
            object = service.createObject(patient, resource);
        } catch (Exception e) {
            log.error(TAG, "Error creating patient.", e);
        }
        return (Patient) object;
    }

    @Override
    public Patient updatePatient(final Patient patient) {
        Object object = null;
        try {
            String searchQuery = "uuid: " + StringUtil.quote(patient.getUuid());
            // before performing update we need to check which resource is being used to create this patient
            Resource patientResource = Context.getResource(Constants.PATIENT_RESOURCE);
            Object savedPatientObject = service.getObject(searchQuery, patientResource);
            if (savedPatientObject != null)
                // update the patient using the patient resource
                object = service.updateObject(patient, patientResource);
            else {
                // check whether this resource is created using the cohort member resource
                Resource memberResource = Context.getResource(Constants.COHORT_MEMBER_RESOURCE);
                Object savedMemberObject = service.getObject(searchQuery, memberResource);
                if (savedMemberObject != null)
                    object = service.updateObject(patient, memberResource);
            }
        } catch (Exception e) {
            log.error(TAG, "Error updating patient.", e);
        }
        return (Patient) object;
    }

    @Override
    public Patient getPatientByUuid(final String uuid) {
        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(uuid))
            searchQuery = "uuid: " + StringUtil.quote(uuid);

        Patient patient = null;
        try {
            patient = service.getObject(searchQuery, Patient.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting patient using query: " + searchQuery, e);
        }
        return patient;
    }

    @Override
    public Patient getPatientByIdentifier(final String identifier) {
        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(identifier))
            searchQuery = "identifier: " + StringUtil.quote(identifier);

        Patient patient = null;
        try {
            patient = service.getObject(searchQuery, Patient.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting patient using query: " + searchQuery, e);
        }
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<Patient>();
        try {
            patients = service.getObjects(StringUtil.EMPTY, Patient.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting all patients.", e);
        }
        return patients;
    }

    @Override
    public List<Patient> getPatientsByName(final String name) {
        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(name))
            searchQuery = "name:" + name + "*";

        List<Patient> patients = new ArrayList<Patient>();
        try {
            patients = service.getObjects(searchQuery, Patient.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting patients using query: " + searchQuery, e);
        }
        return patients;
    }

    @Override
    public List<Patient> searchPatients(final String term) {
        StringBuilder queryBuilder = new StringBuilder();

        // we need to perform search on both patient resource and cohort member resource
        // this is because we mapped both regular patient and cohort member object into single patient object
        Resource patientResource = Context.getResource(Constants.PATIENT_RESOURCE);
        Resource cohortMemberResource = Context.getResource(Constants.COHORT_MEMBER_RESOURCE);

        List<SearchableField> searchableFields = new ArrayList<SearchableField>();
        searchableFields.addAll(patientResource.getSearchableFields());
        searchableFields.addAll(cohortMemberResource.getSearchableFields());

        for (SearchableField searchableField : searchableFields) {
            if (!searchableField.isUnique()) {
                if (!StringUtil.isBlank(queryBuilder.toString()))
                    queryBuilder.append(" OR ");
                String query = searchableField.getName() + ": " + term;
                queryBuilder.append(query);
            }
        }

        List<Patient> patients = new ArrayList<Patient>();
        try {
            String searchQuery = queryBuilder.toString();
            if (!StringUtil.isBlank(searchQuery))
                patients = service.getObjects(searchQuery, Patient.class);
        } catch (Exception e) {
            log.error(TAG, "Error searching patients using query: " + queryBuilder.toString(), e);
        }
        return patients;
    }

    @Override
    public void deletePatient(final Patient patient) {
        try {
            String searchQuery = "uuid: " + StringUtil.quote(patient.getUuid());
            // before performing update we need to check which resource is being used to create this patient
            Resource patientResource = Context.getResource(Constants.PATIENT_RESOURCE);
            Object savedPatientObject = service.getObject(searchQuery, patientResource);
            if (savedPatientObject != null)
                // update the patient using the patient resource
                service.invalidate(patient, patientResource);
            else {
                // check whether this resource is created using the cohort member resource
                Resource memberResource = Context.getResource(Constants.COHORT_MEMBER_RESOURCE);
                Object savedMemberObject = service.getObject(searchQuery, memberResource);
                if (savedMemberObject != null)
                    service.invalidate(patient, memberResource);
            }
        } catch (Exception e) {
            log.error(TAG, "Error deleting patient.", e);
        }
    }

    @Override
    public void deleteAllPatients() {
        // TODO Do we need to implement delete all patients?
    }
}
