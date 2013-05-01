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
import com.mclinic.api.dao.CredentialDao;
import com.mclinic.api.dao.RoleDao;
import com.mclinic.api.dao.UserDao;
import com.mclinic.api.model.Credential;
import com.mclinic.api.model.Privilege;
import com.mclinic.api.model.Role;
import com.mclinic.api.model.User;
import com.mclinic.api.service.UserService;
import com.mclinic.util.Constants;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private CredentialDao credentialDao;

    @Inject
    private RoleDao roleDao;

    protected UserServiceImpl() {
    }

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
        userDao.download(uuid, Constants.UUID_USER_RESOURCE);
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
        userDao.download(name, Constants.SEARCH_USER_RESOURCE);
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
        return userDao.getByUuid(uuid);
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
        return userDao.getByUsername(username);
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
        return userDao.getAll();
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
        try {
            userDao.delete(user, Constants.UUID_USER_RESOURCE);
        } catch (IOException e) {
            userDao.delete(user, Constants.SEARCH_USER_RESOURCE);
        }
    }

    /**
     * Save a new credential record in the local repository.
     *
     * @param credential the new credential to be saved.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should save the new credential record.
     */
    @Override
    public void saveCredential(final Credential credential) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Update a credential record in the local repository.
     *
     * @param credential the credential record to be updated.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should update the credential record.
     */
    @Override
    public void updateCredential(final Credential credential) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get a credential record using the uuid of the record.
     *
     * @param uuid the uuid of the record.
     * @return the credential with matching uuid.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return credential with matching uuid.
     */
    @Override
    public Credential getCredentialByUuid(final String uuid) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get a credential record for a username.
     *
     * @param username the username.
     * @return the credential record for the username.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return credential for the username.
     */
    @Override
    public Credential getCredentialByUsername(final String username) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get all credential records.
     *
     * @return all credential records from the local repository.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return all saved credential records from local repository.
     */
    @Override
    public List<Credential> getAllCredentials() throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Delete a credential record from the local repository.
     *
     * @param credential the credential record to be deleted.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should delete credential from local repository.
     */
    @Override
    public void deleteCredential(final Credential credential) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Download privilege record using the privilege uuid.
     *
     * @param uuid the uuid for the privilege.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download privilege with matching uuid.
     */
    @Override
    public void downloadPrivilege(final String uuid) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Download all privilege records matching the privilege name.
     *
     * @param name the partial name of the privileges.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download all privileges with matching name.
     */
    @Override
    public void downloadPrivileges(final String name) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get privilege from local repository using the privilege uuid.
     *
     * @param uuid the uuid of the privilege.
     * @return the privilege with matching uuid.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return privilege with matching uuid.
     */
    @Override
    public Privilege getPrivilegeByUuid(final String uuid) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get privilege records from local repository using the privilege name.
     *
     * @param name the partial name of the privileges.
     * @return all privileges with matching name.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return all privileges with matching name.
     * @should return empty list when no privilege record match the name.
     */
    @Override
    public List<Privilege> getPrivilegesByName(final String name) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Delete privilege from the local repository.
     *
     * @param privilege the privilege to be deleted.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should delete privilege from the local repository.
     */
    @Override
    public void deletePrivilege(final Privilege privilege) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Download role with matching uuid.
     *
     * @param uuid the uuid of the role.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download role with matching uuid.
     */
    @Override
    public void downloadRole(final String uuid) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Download role with matching name.
     *
     * @param name the name of the role.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download roles with matching name.
     */
    @Override
    public void downloadRoles(final String name) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get role from local repository with matching uuid.
     *
     * @param uuid the uuid of the role.
     * @return the role with matching uuid.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return role with matching uuid.
     */
    @Override
    public Role getRoleByUuid(final String uuid) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get role records from local repository with matching name.
     *
     * @param name the partial name of the role.
     * @return all roles with matching name.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return role records with matching name.
     * @should return empty list when no record matching the name.
     */
    @Override
    public List<Role> getRolesByName(final String name) throws IOException, ParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Delete role record from the local repository.
     *
     * @param role the role record to be deleted.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should delete role record from local repository.
     */
    @Override
    public void deleteRole(final Role role) throws IOException, ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
