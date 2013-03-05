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

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.UserDao;
import com.mclinic.api.model.User;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.resource.Resource;
import com.mclinic.search.api.util.StringUtil;
import com.mclinic.util.Constants;

public class UserDaoImpl implements UserDao {

    @Inject
    private Logger log;

    @Inject
    private RestAssuredService service;

    private static final String TAG = UserDao.class.getSimpleName();

    @Override
    public User createUser(final User user) {
        Object object = null;
        try {
            Resource resource = Context.getResource(Constants.USER_RESOURCE);
            object = service.createObject(user, resource);
        } catch (Exception e) {
            log.error(TAG, "Error creating user.", e);
        }
        return (User) object;
    }

    @Override
    public User updateUser(final User user) {
        Object object = null;
        try {
            Resource resource = Context.getResource(Constants.USER_RESOURCE);
            object = service.updateObject(user, resource);
        } catch (Exception e) {
            log.error(TAG, "Error updating user.", e);
        }
        return (User) object;
    }

    @Override
    public User getUserByUuid(final String uuid) {
        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(uuid))
            searchQuery = "uuid: " + StringUtil.quote(uuid);

        User user = null;
        try {
            user = service.getObject(searchQuery, User.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting user using query: " + searchQuery, e);
        }
        return user;
    }

    @Override
    public User getUserByUsername(final String username) {
        String searchQuery = StringUtil.EMPTY;
        // special query string for the user
        // the structure from the rest is: "username - full name"
        // TODO: maybe we need to change the unique field of the user to the username
        if (!StringUtil.isEmpty(username))
            searchQuery = "display: " + username + "\\ \\-\\ ";

        User user = null;
        try {
            user = service.getObject(searchQuery, User.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting user using query: " + searchQuery, e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            users = service.getObjects(StringUtil.EMPTY, User.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting all users.", e);
        }
        return users;
    }

    @Override
    public void deleteUser(final User user) {
        try {
            Resource resource = Context.getResource(Constants.USER_RESOURCE);
            service.invalidate(user, resource);
        } catch (Exception e) {
            log.error(TAG, "Error deleting user.", e);
        }
    }

    @Override
    public void deleteAllUsers() {
        // TODO Do we need to implement delete all users?
    }
}
