package com.mclinic.api.service;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.User;
import com.mclinic.api.service.impl.UserServiceImpl;

/**
 * Service handling all operation to the @{User} actor/model
 *
 * TODO: add ability to search based on lucene like query syntax (merging name and identifier).
 */
@ImplementedBy(UserServiceImpl.class)
public interface UserService {

    User createUser(final User user);

    User updateUser(final User user);

    /**
     * @param uuid the user uuid
     * @return user with matching uuid or null when no user match the uuid
     * @should return user with matching uuid
     * @should return null when no user match the uuid
     */
    User getUserByUuid(final String uuid);

    /**
     * @param username the user username
     * @return user with matching username or null when no user match the username
     * @should return user with matching username
     * @should return null when no user match the username
     */
    User getUserByUsername(final String username);

    /**
     * @return all registered users or empty list when no user is registered
     * @should return all registered users
     * @should return empty list when no user is registered
     */
    List<User> getAllUsers();

    void deleteUser(final User user);

    void deleteAllUsers();

}
