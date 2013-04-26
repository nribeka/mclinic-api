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
package com.mclinic.api.service.impl;

import com.google.inject.Inject;
import com.mclinic.api.dao.UserDao;
import com.mclinic.api.model.User;
import com.mclinic.api.service.UserService;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao dao;

    /**
     * Download a single user record from the user rest resource into the local lucene repository.
     *
     * @param uuid the uuid of the user.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download user with matching uuid.
     */
    @Override
    public void downloadUserByUuid(final String uuid) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Download all users with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the user to be downloaded. When empty, will return all users available.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download all user with partially matched name.
     * @should download all user when name is empty.
     */
    @Override
    public void downloadUsersByName(final String name) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get a single user using the user's uuid.
     *
     * @param uuid the user uuid.
     * @return user with matching uuid or null when no user match the uuid.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return user with matching uuid.
     * @should return null when no user match the uuid.
     */
    @Override
    public User getUserByUuid(final String uuid) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get a single user using the user name.
     *
     * @param username the user username.
     * @return user with matching username or null when no user match the username.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return user with matching username.
     * @should return null when no user match the username.
     */
    @Override
    public User getUserByUsername(final String username) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get all saved users in the local repository.
     *
     * @return all registered users or empty list when no user is registered.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return all registered users.
     * @should return empty list when no user is registered.
     */
    @Override
    public List<User> getAllUsers() throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Delete a user record from the local repository.
     *
     * @param user the user to be deleted.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should delete the user record from the local repository.
     */
    @Override
    public void deleteUser(final User user) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
