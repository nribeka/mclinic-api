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

import com.google.inject.Injector;
import com.mclinic.api.service.CohortService;
import com.mclinic.api.service.FormService;
import com.mclinic.api.service.ObservationService;
import com.mclinic.api.service.PatientService;
import com.mclinic.api.service.UserService;

/**
 * TODO: Write brief description about the class here.
 */
public class Context {

    private Injector injector;

    private static final ThreadLocal<UserContext> userContextHolder = new ThreadLocal<UserContext>();

    public Context(final Injector injector) {
        this.injector = injector;
    }

    private UserContext getUserContext() {
        return userContextHolder.get();
    }

    private void setUserContext(final UserContext userContext) {
        userContextHolder.set(userContext);
    }

    private void removeUserContext() {
        userContextHolder.remove();
    }

    public void openSession() {
        setUserContext(new UserContext());
    }

    public void closeSession() {
        removeUserContext();
    }

    //TODO: Need to throw AuthorizationException when userContext is null
    public void authenticate(final String username, final String password) {
        getUserContext().authenticate(username, password);
    }

    public void deauthenticate() {
        getUserContext().logout();
    }

    public boolean isAuthenticated() {
        return getUserContext() != null && getUserContext().isAuthenticated();
    }

    // TODO: Need to throw exception when the injector is still null
    public CohortService getCohortService() {
        return injector.getInstance(CohortService.class);
    }

    public FormService getFormService() {
        return injector.getInstance(FormService.class);
    }

    public ObservationService getObservationService() {
        return injector.getInstance(ObservationService.class);
    }

    public PatientService getPatientService() {
        return injector.getInstance(PatientService.class);
    }

    public UserService getUserService() {
        return injector.getInstance(UserService.class);
    }

    public <T> T getService(final Class<T> serviceClass) {
        return injector.getInstance(serviceClass);
    }
}
