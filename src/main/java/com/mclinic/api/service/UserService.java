package com.mclinic.api.service;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Credential;
import com.mclinic.api.model.Privilege;
import com.mclinic.api.model.Role;
import com.mclinic.api.model.User;
import com.mclinic.api.service.impl.UserServiceImpl;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Service handling all operation to the @{User} actor/model
 * <p/>
 * TODO: add ability to search based on lucene like query syntax (merging name and identifier).
 */
@ImplementedBy(UserServiceImpl.class)
public interface UserService {

    /**
     * Download a single user record from the user rest resource into the local lucene repository.
     *
     * @param uuid the uuid of the user.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download user with matching uuid.
     */
    User downloadUserByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Download a single user record from the user rest resource into the local lucene repository.
     *
     * @param username the username of the user.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download user with matching uuid.
     */
    User downloadUserByUsername(final String username) throws IOException, ParseException;

    /**
     * Download all users with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the user to be downloaded. When empty, will return all users available.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download all user with partially matched name.
     * @should download all user when name is empty.
     */
    List<User> downloadUsersByName(final String name) throws IOException, ParseException;

    /**
     * Get a single user using the user's uuid.
     *
     * @param uuid the user uuid.
     * @return user with matching uuid or null when no user match the uuid.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return user with matching uuid.
     * @should return null when no user match the uuid.
     */
    User getUserByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Get user using the user's name.
     *
     * @param name the name of the user.
     * @return user with matching name or null when no user match the name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return user with matching username.
     * @should return null when no user match the username.
     */
    List<User> getUserByName(final String name) throws IOException, ParseException;

    /**
     * Get a single user using the user name.
     *
     * @param username the user username.
     * @return user with matching username or null when no user match the username.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return user with matching username.
     * @should return null when no user match the username.
     */
    User getUserByUsername(final String username) throws IOException, ParseException;

    /**
     * Get all saved users in the local repository.
     *
     * @return all registered users or empty list when no user is registered.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return all registered users.
     * @should return empty list when no user is registered.
     */
    List<User> getAllUsers() throws IOException, ParseException;

    /**
     * Delete a user record from the local repository.
     *
     * @param user the user to be deleted.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should delete the user record from the local repository.
     */
    void deleteUser(final User user) throws IOException, ParseException;

    /**
     * Save a new credential record in the local repository.
     *
     * @param credential the new credential to be saved.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should save the new credential record.
     */
    void saveCredential(final Credential credential) throws IOException, ParseException;

    /**
     * Update a credential record in the local repository.
     *
     * @param credential the credential record to be updated.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should update the credential record.
     */
    void updateCredential(final Credential credential) throws IOException, ParseException;

    /**
     * Get a credential record using the uuid of the record.
     *
     * @param uuid the uuid of the record.
     * @return the credential with matching uuid.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return credential with matching uuid.
     */
    Credential getCredentialByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Get a credential record for a username.
     *
     * @param username the username.
     * @return the credential record for the username.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return credential for the username.
     */
    Credential getCredentialByUsername(final String username) throws IOException, ParseException;

    /**
     * Get all credential records.
     *
     * @return all credential records from the local repository.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return all saved credential records from local repository.
     */
    List<Credential> getAllCredentials() throws IOException, ParseException;

    /**
     * Delete a credential record from the local repository.
     *
     * @param credential the credential record to be deleted.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should delete credential from local repository.
     */
    void deleteCredential(final Credential credential) throws IOException, ParseException;

    /**
     * Download privilege record using the privilege uuid.
     *
     * @param uuid the uuid for the privilege.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download privilege with matching uuid.
     */
    Privilege downloadPrivilege(final String uuid) throws IOException, ParseException;

    /**
     * Download all privilege records matching the privilege name.
     *
     * @param name the partial name of the privileges.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download all privileges with matching name.
     */
    List<Privilege> downloadPrivileges(final String name) throws IOException, ParseException;

    /**
     * Get privilege from local repository using the privilege uuid.
     *
     * @param uuid the uuid of the privilege.
     * @return the privilege with matching uuid.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return privilege with matching uuid.
     */
    Privilege getPrivilegeByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Get privilege records from local repository using the privilege name.
     *
     * @param name the partial name of the privileges.
     * @return all privileges with matching name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return all privileges with matching name.
     * @should return empty list when no privilege record match the name.
     */
    List<Privilege> getPrivilegesByName(final String name) throws IOException, ParseException;

    /**
     * Delete privilege from the local repository.
     *
     * @param privilege the privilege to be deleted.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should delete privilege from the local repository.
     */
    void deletePrivilege(final Privilege privilege) throws IOException, ParseException;

    /**
     * Download role with matching uuid.
     *
     * @param uuid the uuid of the role.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download role with matching uuid.
     */
    Role downloadRole(final String uuid) throws IOException, ParseException;

    /**
     * Download role with matching name.
     *
     * @param name the name of the role.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download roles with matching name.
     */
    List<Role> downloadRoles(final String name) throws IOException, ParseException;

    /**
     * Get role from local repository with matching uuid.
     *
     * @param uuid the uuid of the role.
     * @return the role with matching uuid.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return role with matching uuid.
     */
    Role getRoleByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Get role records from local repository with matching name.
     *
     * @param name the partial name of the role.
     * @return all roles with matching name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return role records with matching name.
     * @should return empty list when no record matching the name.
     */
    List<Role> getRolesByName(final String name) throws IOException, ParseException;

    /**
     * Delete role record from the local repository.
     *
     * @param role the role record to be deleted.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should delete role record from local repository.
     */
    void deleteRole(final Role role) throws IOException, ParseException;
}
