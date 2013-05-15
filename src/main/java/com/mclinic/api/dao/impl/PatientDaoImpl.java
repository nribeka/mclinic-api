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

import com.mclinic.api.dao.PatientDao;
import com.mclinic.api.model.Patient;
import com.mclinic.search.api.filter.Filter;
import com.mclinic.search.api.filter.FilterFactory;
import com.mclinic.search.api.util.CollectionUtil;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientDaoImpl extends OpenmrsDaoImpl<Patient> implements PatientDao {

    private static final String TAG = PatientDao.class.getSimpleName();

    protected PatientDaoImpl() {
        super(Patient.class);
    }

    /**
     * Get patient by using the identifier.
     *
     * @param identifier the identifier of the patient.
     * @return the patient with matching identifier.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    @Override
    public Patient getByIdentifier(final String identifier) throws ParseException, IOException {
        Patient patient = null;
        List<Filter> filters = new ArrayList<Filter>();
        if (!StringUtil.isEmpty(identifier)) {
            Filter filter = FilterFactory.createFilter("identifier", identifier);
            filters.add(filter);
        }
        List<Patient> patients = service.getObjects(filters, Patient.class);
        if (!CollectionUtil.isEmpty(patients)) {
            if (patients.size() > 1)
                throw new IOException("Unable to uniquely identify a Patient using the identifier");
            patient = patients.get(0);
        }
        return patient;
    }

    /**
     * Get cohort by the name of the cohort. Passing empty string will returns all registered cohorts.
     *
     * @param name the partial name of the cohort or empty string.
     * @return the list of all matching cohort on the cohort name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    @Override
    public List<Patient> getByName(final String name) throws ParseException, IOException {
        StringBuilder query = new StringBuilder();
        if (!StringUtil.isEmpty(name)) {
            query.append("givenName:").append(name).append("*").append(" OR ");
            query.append("middleName:").append(name).append("*").append(" OR ");
            query.append("familyName:").append(name).append("*");
        }
        return service.getObjects(query.toString(), Patient.class);
    }

    /**
     * Search for patients matching the term on name and identifier.
     *
     * @param term the term that should match.
     * @return all patients with matching name or identifier.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    @Override
    public List<Patient> search(final String term) throws ParseException, IOException {
        if (!StringUtil.isEmpty(term)) {
            if (containsDigit(term)) {
                Filter filter = FilterFactory.createFilter("identifier", term + "*");
                return service.getObjects(Arrays.asList(filter), Patient.class);
            } else {
                StringBuilder query = new StringBuilder();
                query.append("givenName:").append(term).append("*").append(" OR ");
                query.append("middleName:").append(term).append("*").append(" OR ");
                query.append("familyName:").append(term).append("*");
                return service.getObjects(query.toString(), Patient.class);
            }
        }
        return service.getObjects(StringUtil.EMPTY, Patient.class);
    }

    private boolean containsDigit(final String term) {
        for (char c : term.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
