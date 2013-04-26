package com.mclinic.api.service;

import com.google.inject.ImplementedBy;
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
    void downloadUserByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Download all users with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the user to be downloaded. When empty, will return all users available.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download all user with partially matched name.
     * @should download all user when name is empty.
     */
    void downloadUsersByName(final String name) throws IOException, ParseException;

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
}
