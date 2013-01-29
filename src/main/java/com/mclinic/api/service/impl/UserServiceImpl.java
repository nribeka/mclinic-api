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

import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.UserDao;
import com.mclinic.api.model.User;
import com.mclinic.api.service.UserService;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao dao;

    @Override
    public User createUser(final User user) {
        return dao.createUser(user);
    }

    @Override
    public User updateUser(final User user) {
        return dao.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public User getUserByUsername(final String username) {
        return dao.getUserByUsername(username);
    }

    @Override
    public void deleteUser(final User user) {
        dao.deleteUser(user);
    }

    @Override
    public void deleteAllUsers() {
        dao.deleteAllUsers();
    }

    @Override
    public User getUserByUuid(final String uuid) {
        return dao.getUserByUuid(uuid);
    }
}
