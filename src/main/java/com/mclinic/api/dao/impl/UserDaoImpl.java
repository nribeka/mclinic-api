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

import com.mclinic.api.dao.UserDao;
import com.mclinic.api.model.User;
import com.mclinic.search.api.util.CollectionUtil;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

public class UserDaoImpl extends OpenmrsDaoImpl<User> implements UserDao {

    private static final String TAG = UserDao.class.getSimpleName();

    protected UserDaoImpl() {
        super(User.class);
    }

    /**
     * Get a user record by the user name of the user.
     *
     * @param username the username of the user.
     * @return user with matching username.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    @Override
    public User getByUsername(final String username) throws ParseException, IOException {
        User user = null;
        StringBuilder query = new StringBuilder();
        if (!StringUtil.isEmpty(username)) {
            query.append("username:").append(username).append(" OR ");
            query.append("systemId:").append(username);
        }
        List<User> users = service.getObjects(query.toString(), User.class);
        if (!CollectionUtil.isEmpty(users)) {
            if (users.size() > 1) {
                throw new IOException("Unable to uniquely identify a Patient using the identifier");
            }
            user = users.get(0);
        }
        return user;
    }

    /**
     * Get user by the name of the user. Passing empty string will returns all registered users.
     *
     * @param name the partial name of the user or empty string.
     * @return the list of all matching user on the user name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     */
    @Override
    public List<User> getByName(final String name) throws ParseException, IOException {
        StringBuilder query = new StringBuilder();
        if (!StringUtil.isEmpty(name)) {
            query.append("givenName:").append(name).append("*").append(" OR ");
            query.append("middleName:").append(name).append("*").append(" OR ");
            query.append("familyName:").append(name).append("*");
        }
        return service.getObjects(query.toString(), User.class);
    }
}
