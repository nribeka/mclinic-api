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
package com.mclinic.api.context;

import com.mclinic.api.config.Configuration;
import com.mclinic.api.exception.AuthenticationException;
import com.mclinic.api.model.Credential;
import com.mclinic.api.model.User;
import com.mclinic.api.service.UserService;
import com.mclinic.search.api.util.DigestUtil;
import com.mclinic.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.UUID;

/**
 * TODO: Write brief description about the class here.
 */
class UserContext {

    private User user;

    private Credential credential;

    private Configuration configuration;

    UserContext() {
    }

    /**
     * Authenticate user using the username and password on the url.
     *
     * @param username the username.
     * @param password the password.
     */
    public void authenticate(final String username, final String password,
                             final UserService userService)
            throws IOException, ParseException {
        // TODO: Need to update this authentication method.
        // Process:
        // * Download the user by the username first.
        // * If we get a user, we write the current user credential object. The context is now authenticated.
        // * If we don't get a user record, we search the credential object from local repo.
        // * Match the credential's password with this password (password is salted).
        // * If we found a match, get the user with the username. The context is now authenticated.
        user = userService.getUserByUsername(username);
        if (user == null) {
            user = userService.downloadUserByUsername(username);
            if (user != null) {
                String uuid = UUID.randomUUID().toString();
                String salt = DigestUtil.getSHA1Checksum(uuid);
                String hashedPassword = DigestUtil.getSHA1Checksum(salt + ":" + password);

                credential = new Credential();
                credential.setUuid(uuid);
                credential.setSalt(salt);
                credential.setUserUuid(uuid);
                credential.setUsername(username);
                credential.setPassword(hashedPassword);
                userService.saveCredential(credential);
            } else {
                throw new AuthenticationException("Unable to authenticate user for username: " + username);
            }
        } else {
            credential = userService.getCredentialByUsername(username);
            String salt = credential.getSalt();
            String hashedPassword = DigestUtil.getSHA1Checksum(salt + ":" + password);
            if (!StringUtil.equals(hashedPassword, credential.getPassword())) {
                throw new IOException("Unable to authenticate user for username: " + username);
            }
        }
    }

    /**
     * Get currently authenticated user.
     *
     * @return active user who has been authenticated.
     */
    public User getAuthenticatedUser() {
        return user;
    }

    /**
     * Get whether this user context have been authenticated or not.
     *
     * @return true if user has been authenticated in this UserContext
     */
    public boolean isAuthenticated() {
        return user != null;
    }

    /**
     * Logs out the "active" (authenticated) user within this UserContext
     *
     * @see #authenticate
     */
    public void deauthenticate() {
        user = null;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(final Configuration configuration) {
        this.configuration = configuration;
    }
}
