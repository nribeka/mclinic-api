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

import com.mclinic.api.dao.CredentialDao;
import com.mclinic.api.model.Credential;
import com.mclinic.search.api.filter.Filter;
import com.mclinic.search.api.filter.FilterFactory;
import com.mclinic.search.api.util.CollectionUtil;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CredentialDaoImpl extends LocalDaoImpl<Credential> implements CredentialDao {

    private static final String TAG = CredentialDao.class.getSimpleName();

    protected CredentialDaoImpl() {
        super(Credential.class);
    }

    /**
     * Get a credential record by the username of the user.
     *
     * @param username the username of the user.
     * @return credential with matching username.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    @Override
    public Credential getByUsername(final String username) throws ParseException, IOException {
        Credential credential = null;
        List<Filter> filters = new ArrayList<Filter>();
        if (!StringUtil.isEmpty(username)) {
            Filter filter = FilterFactory.createFilter("username", username);
            filters.add(filter);
        }
        List<Credential> credentials = service.getObjects(filters, Credential.class);
        if (!CollectionUtil.isEmpty(credentials)) {
            if (credentials.size() > 1)
                throw new IOException("Unable to uniquely identify a Patient using the identifier");
            credential = credentials.get(0);
        }
        return credential;
    }

}
