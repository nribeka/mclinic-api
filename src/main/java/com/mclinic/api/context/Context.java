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

import com.mclinic.api.service.CohortService;
import com.mclinic.api.service.FormService;
import com.mclinic.api.service.ObservationService;
import com.mclinic.api.service.PatientService;
import com.mclinic.api.service.UserService;

/**
 * TODO: Write brief description about the class here.
 */
public class Context {

    private static final ServiceContext serviceContext = ServiceContext.getInstance();

    private static final ThreadLocal<UserContext> credentialHolder = new ThreadLocal<UserContext>();

    private Context() {
    }

    private static UserContext getUserContext() {
        return credentialHolder.get();
    }

    private static void setUserContext(final UserContext userContext) {
        credentialHolder.set(userContext);
    }

    private static void removeUserContext() {
        credentialHolder.remove();
    }

    private static ServiceContext getServiceContext() {
        return serviceContext;
    }

    public static void openSession() {
        setUserContext(new UserContext());
    }

    public static void closeSession() {
        removeUserContext();
    }

    //TODO: Need to throw AuthorizationException when userContext is null
    public static void authenticate(final String username, final String password) {
        getUserContext().authenticate(username, password);
    }

    public static void deauthenticate() {
        getUserContext().logout();
    }

    public static boolean isAuthenticated() {
        return getUserContext() != null && getUserContext().isAuthenticated();
    }

    public static void startService() {
        getServiceContext().start();
    }

    public static CohortService getCohortService() {
        return getServiceContext().getService(CohortService.class);
    }

    public static FormService getFormService() {
        return getServiceContext().getService(FormService.class);
    }

    public static ObservationService getObservationService() {
        return getServiceContext().getService(ObservationService.class);
    }

    public static PatientService getPatientService() {
        return getServiceContext().getService(PatientService.class);
    }

    public static UserService getUserService() {
        return getServiceContext().getService(UserService.class);
    }

    public static <T> T getService(final Class<T> serviceClass) {
        return getServiceContext().getService(serviceClass);
    }
}
