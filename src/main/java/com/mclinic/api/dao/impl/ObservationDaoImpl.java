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

import com.mclinic.api.dao.ObservationDao;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

public class ObservationDaoImpl extends OpenmrsDaoImpl<Observation> implements ObservationDao {

    private static final String TAG = ObservationDao.class.getSimpleName();

    public ObservationDaoImpl() {
        super(Observation.class);
    }

    @Override
    public List<Observation> getAll(final Patient patient) throws ParseException, IOException {
        String searchQuery = StringUtil.EMPTY;
        if (patient != null && !StringUtil.isEmpty(patient.getUuid()))
            searchQuery = "patientUuid: " + StringUtil.quote(patient.getUuid());
        return service.getObjects(searchQuery, Observation.class);
    }

    @Override
    public List<Observation> search(final Patient patient, final String term) throws ParseException, IOException {
        String patientQuery = StringUtil.EMPTY;
        if (patient != null && !StringUtil.isEmpty(patient.getUuid()))
            patientQuery = "(patientUuid: " + StringUtil.quote(patient.getUuid()) + ")";
        // TODO: fix the search query later on!
        return service.getObjects(patientQuery + "(" + patientQuery + ")", Observation.class);
    }
}
