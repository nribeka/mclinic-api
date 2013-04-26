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

import com.mclinic.api.dao.CohortDao;
import com.mclinic.api.dao.RoleDao;
import com.mclinic.api.model.Role;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

public class RoleDaoImpl extends OpenmrsDaoImpl<Role> implements RoleDao {

    private static final String TAG = CohortDao.class.getSimpleName();

    public RoleDaoImpl() {
        super(Role.class);
    }

    /**
     * Get role by the name of the role. Passing empty string will returns all registered roles.
     *
     * @param name the partial name of the role or empty string.
     * @return the list of all matching role on the role name.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when parsing lucene query in the internal saving process happen.
     * @throws java.io.IOException when reading resource descriptor happen.
     */
    @Override
    public List<Role> getByName(final String name) throws ParseException, IOException {
        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(name))
            searchQuery = "name: " + name + "*";
        return service.getObjects(searchQuery, Role.class);
    }
}
