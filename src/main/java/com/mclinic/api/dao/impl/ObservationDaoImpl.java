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
import com.mclinic.search.api.filter.Filter;
import com.mclinic.search.api.filter.FilterFactory;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObservationDaoImpl extends OpenmrsDaoImpl<Observation> implements ObservationDao {

    private static final String TAG = ObservationDao.class.getSimpleName();

    protected ObservationDaoImpl() {
        super(Observation.class);
    }

    /**
     * Search observations for patient with matching partial search term.
     *
     * @param patientUuid the uuid of the patient.
     * @param conceptName the search term for the question of the observations.
     * @return all observations for the patient with question matching the search term.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    @Override
    public List<Observation> search(final String patientUuid, final String conceptName) throws ParseException, IOException {
        List<Filter> filters = new ArrayList<Filter>();
        if (!StringUtil.isEmpty(patientUuid)) {
            Filter patientFilter = FilterFactory.createFilter("patientUuid", patientUuid);
            filters.add(patientFilter);
        }
        if (!StringUtil.isEmpty(conceptName)) {
            Filter conceptFilter = FilterFactory.createFilter("conceptName", conceptName);
            filters.add(conceptFilter);
        }
        return service.getObjects(filters, Observation.class);
    }
}
