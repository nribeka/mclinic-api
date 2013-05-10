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
import com.mclinic.api.dao.OpenmrsDao;
import com.mclinic.api.model.OpenmrsSearchable;
import com.mclinic.search.api.context.ServiceContext;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;

public abstract class OpenmrsDaoImpl<T extends OpenmrsSearchable> extends SearchableDaoImpl<T> implements OpenmrsDao<T> {

    @Inject
    private ServiceContext serviceContext;

    protected OpenmrsDaoImpl(final Class<T> daoClass) {
        super(daoClass);
    }

    /**
     * Download the searchable object matching the uuid. This process involve executing the REST call, pulling the
     * resource and then saving it to local lucene repository.
     *
     * @param term     the term to be passed to search object to filter the searchable object.
     * @param resource resource descriptor used to convert the resource to the correct object.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     *                        TODO: Change the signature of this to return list of downloaded objects.
     *                        TODO: Change the signature of the service to match the param (uuid return one, name return list).
     */
    @Override
    public void download(final String term, final String resource) throws ParseException, IOException {
        service.loadObjects(term, serviceContext.getResource(resource));
    }

    /**
     * Get the OpenMRS searchable object using the uuid.
     *
     * @param uuid the uuid of the searchable object.
     * @return the searchable object.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    public T getByUuid(final String uuid) throws ParseException, IOException {
        return service.getObject(uuid, daoClass);
    }
}
