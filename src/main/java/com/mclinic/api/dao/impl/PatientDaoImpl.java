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
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

public class PatientDaoImpl extends OpenmrsDaoImpl<Patient> implements PatientDao {

    private static final String TAG = PatientDao.class.getSimpleName();

    public PatientDaoImpl() {
        super(Patient.class);
    }

    @Override
    public Patient getByIdentifier(final String identifier) throws ParseException, IOException {
        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(identifier))
            searchQuery = "identifier: " + StringUtil.quote(identifier);
        return service.getObject(searchQuery, Patient.class);
    }

    @Override
    public List<Patient> getByName(final String name) throws ParseException, IOException {
        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(name))
            searchQuery = "name:" + name + "*";
        return service.getObjects(searchQuery, Patient.class);
    }

    @Override
    public List<Patient> search(final String term) throws ParseException, IOException {
        // TODO: fix this search patients query
        return service.getObjects(term, Patient.class);
    }
}
