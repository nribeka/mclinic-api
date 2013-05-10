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

import com.google.inject.Inject;
import com.mclinic.api.dao.SearchableDao;
import com.mclinic.search.api.context.ServiceContext;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.model.object.BaseSearchable;
import com.mclinic.search.api.service.RestAssuredService;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * TODO: Write brief description about the class here.
 */
public abstract class SearchableDaoImpl<T extends BaseSearchable> implements SearchableDao<T> {

    @Inject
    protected Logger logger;

    protected Class<T> daoClass;

    @Inject
    protected ServiceContext context;

    @Inject
    protected RestAssuredService service;

    protected SearchableDaoImpl(final Class<T> daoClass) {
        this.daoClass = daoClass;
    }

    /**
     * Get all searchable object for the particular type.
     *
     * @return list of all searchable object or empty list.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    @Override
    public List<T> getAll() throws ParseException, IOException {
        return service.getObjects(StringUtil.EMPTY, daoClass);
    }
}
