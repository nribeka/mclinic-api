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
import org.junit.Test;

/**
 * TODO: Write brief description about the class here.
 */
public class CohortServiceTest {

    @Test
    public void aspectTest() throws Exception {

        Context context = ContextFactory.createContext();
        CohortService cohortService = context.getCohortService();
        cohortService.getAllCohorts();

        System.out.println();
        System.out.println("Authentication ...");

        context.openSession();
        context.authenticate("username", "password");
        cohortService.getAllCohorts();
        context.closeSession();

        cohortService.getAllCohorts();
    }
}
