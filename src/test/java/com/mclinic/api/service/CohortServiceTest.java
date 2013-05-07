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
package com.mclinic.api.service;

import com.mclinic.api.context.Context;
import com.mclinic.api.context.ContextFactory;
import com.mclinic.api.model.Cohort;
import com.mclinic.search.api.util.StringUtil;
import org.junit.Test;

import java.util.List;

/**
 * TODO: Write brief description about the class here.
 */
public class CohortServiceTest {

    @Test
    public void aspectTest() throws Exception {
        Context context = ContextFactory.createContext();

        context.openSession();
        if (!context.isAuthenticated())
            context.authenticate("admin", "test", "http://localhost:8081/openmrs-standalone");

        context = ContextFactory.createContext();

        CohortService cohortService = context.getCohortService();
        cohortService.downloadCohortsByName(StringUtil.EMPTY);
        List<Cohort> cohorts = cohortService.getAllCohorts();

        for (Cohort cohort : cohorts)
            System.out.println("Cohort: " + cohort.getName() + " | " + cohort.getUuid());

        context.deauthenticate();
        context.closeSession();
    }
}
