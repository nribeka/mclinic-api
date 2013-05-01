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

import com.mclinic.api.model.Credential;
import com.mclinic.api.model.User;

/**
 * TODO: Write brief description about the class here.
 */
class UserContext {

    private User user;

    private Credential credential;

    /**
     * Authenticate user using the username and password.
     *
     * @param username the username.
     * @param password the password.
     */
    public void authenticate(String username, String password) {
        //TODO: search for the correct user and credential and then set it here.
        user = new User();
    }

    /**
     * Get currently authenticated user.
     *
     * @return active user who has been authenticated, otherwise <code>null</code>
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
    public void logout() {
        user = null;
    }
}
