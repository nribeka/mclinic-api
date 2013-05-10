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

import com.mclinic.api.dao.FormDataDao;
import com.mclinic.api.model.FormData;
import com.mclinic.search.api.filter.Filter;
import com.mclinic.search.api.filter.FilterFactory;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormDataDaoImpl extends LocalDaoImpl<FormData> implements FormDataDao {

    private static final String TAG = FormDataDaoImpl.class.getSimpleName();

    protected FormDataDaoImpl() {
        super(FormData.class);
    }

    /**
     * Get all searchable form data with a particular status.
     *
     * @param patientUuid the patient's uuid associated to this form data.
     * @param userUuid    user's uuid associated to this form data.
     * @param status      the status of this form data.
     * @return list of all searchable object or empty list.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     */
    @Override
    public List<FormData> getAll(final String patientUuid, final String userUuid, final String status)
            throws ParseException, IOException {
        List<Filter> filters = new ArrayList<Filter>();
        if (!StringUtil.isEmpty(patientUuid)) {
            Filter patientFilter = FilterFactory.createFilter("patientUuid", patientUuid);
            filters.add(patientFilter);
        }
        if (!StringUtil.isEmpty(userUuid)) {
            Filter userFilter = FilterFactory.createFilter("userUuid", userUuid);
            filters.add(userFilter);
        }
        if (!StringUtil.isEmpty(status)) {
            Filter statusFilter = FilterFactory.createFilter("status", status);
            filters.add(statusFilter);
        }
        return service.getObjects(filters, daoClass);
    }
}
