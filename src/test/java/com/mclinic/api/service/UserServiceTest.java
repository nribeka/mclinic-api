/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package com.mclinic.api.service;

import com.mclinic.api.model.User;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.search.api.Context;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class UserServiceTest {

    private UserService userService;

    private AdministrativeService service;

    @Before
    public void prepare() throws Exception {
        URL repositoryPath = AdministrativeServiceTest.class.getResource("../j2l");
        URL lucenePath = AdministrativeServiceTest.class.getResource("../lucene");
        Context.initialize(new MuzimaModule(lucenePath.getPath(), "uuid"));

        service = Context.getInstance(AdministrativeService.class);
        Assert.assertNotNull(service);

        service.initializeRepository(repositoryPath.getPath());

        URL jsonPath = AdministrativeServiceTest.class.getResource("../json/user");
        service.loadUsers(new File(jsonPath.getPath()));

        userService = Context.getInstance(UserService.class);
        Assert.assertNotNull(userService);
    }

    @After
    public void cleanUp() {
        URL lucenePath = AdministrativeServiceTest.class.getResource("../lucene");

        File luceneDirectory = new File(lucenePath.getPath());
        for (String filename : luceneDirectory.list()) {
            File file = new File(luceneDirectory, filename);
            Assert.assertTrue(file.delete());
        }
    }

    /**
     * @verifies return user with matching uuid
     * @see UserService#getUserByUuid(String)
     */
    @Test
    public void getUserByUuid_shouldReturnUserWithMatchingUuid() throws Exception {
        String uuid = "842f4bae-1692-11df-97a5-7038c432aabf";
        User user = userService.getUserByUuid(uuid);
        Assert.assertNotNull(user);
    }

    /**
     * @verifies return null when no user match the uuid
     * @see UserService#getUserByUuid(String)
     */
    @Test
    public void getUserByUuid_shouldReturnNullWhenNoUserMatchTheUuid() throws Exception {
        String randomUuid = "1234";
        User user = userService.getUserByUuid(randomUuid);
        Assert.assertNull(user);
    }

    /**
     * @verifies return user with matching username
     * @see UserService#getUserByUsername(String)
     */
    @Test
    public void getUserByUsername_shouldReturnUserWithMatchingUsername() throws Exception {
        String username = "admin";
        User user = userService.getUserByUsername(username);
        Assert.assertNotNull(user);
    }

    /**
     * @verifies return null when no user match the username
     * @see UserService#getUserByUsername(String)
     */
    @Test
    public void getUserByUsername_shouldReturnNullWhenNoUserMatchTheUsername() throws Exception {
        String randomUsername = "administrator";
        User user = userService.getUserByUsername(randomUsername);
        Assert.assertNull(user);
    }

    /**
     * @verifies return all registered users
     * @see UserService#getAllUsers()
     */
    @Test
    public void getAllUsers_shouldReturnAllRegisteredUsers() throws Exception {
        List<User> users = userService.getAllUsers();
        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());
    }

    /**
     * @verifies return empty list when no user is registered
     * @see UserService#getAllUsers()
     */
    @Test
    public void getAllUsers_shouldReturnEmptyListWhenNoUserIsRegistered() throws Exception {
        List<User> users = userService.getAllUsers();
        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());

        for (User user : users)
            userService.deleteUser(user);

        users = userService.getAllUsers();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.isEmpty());
    }
}
