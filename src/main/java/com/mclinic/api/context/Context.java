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

import com.google.inject.Singleton;

/**
 * TODO: Write brief description about the class here.
 */
@Singleton
public class Context {

    private static final ThreadLocal<UserContext> credentialHolder = new ThreadLocal<UserContext>();

    public static UserContext getUserContext() {
        return credentialHolder.get();
    }

    public static void setUserContext(final UserContext userContext) {
        credentialHolder.set(userContext);
    }

    public static void removeUserContext() {
        credentialHolder.remove();
    }

    public static void authenticate(String username, String password) {
        getUserContext().authenticate(username, password);
    }

    public static boolean isAuthenticated() {
        return getUserContext().isAuthenticated();
    }
}
