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
package com.mclinic.api.dao;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.RoleDaoImpl;
import com.mclinic.api.model.Role;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

@ImplementedBy(RoleDaoImpl.class)
public interface RoleDao extends OpenmrsDao<Role> {

    /**
     * Get role by the name of the role. Passing empty string will returns all registered roles.
     *
     * @param name the partial name of the role or empty string.
     * @return the list of all matching role on the role name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    List<Role> getByName(final String name) throws ParseException, IOException;
}
