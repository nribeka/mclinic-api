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

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.service.impl.CohortServiceImpl;

/**
 * Service handling all operation to the @{Cohort} actor/model
 */
@ImplementedBy(CohortServiceImpl.class)
public interface CohortService {

    Cohort createCohort(final Cohort cohort);

    Cohort updateCohort(final Cohort cohort);

    /**
     * @param uuid the cohort uuid
     * @return cohort with matching uuid or null when no cohort match the uuid
     * @should return cohort with matching uuid
     * @should return null when no cohort match the uuid
     */
    Cohort getCohortByUuid(final String uuid);

    /**
     * @param name the partial name of the cohort
     * @return list of all cohorts with matching uuid or empty list when no cohort match the name
     * @should return list of all cohorts with matching name
     * @should return empty list when no cohort match the name
     */
    List<Cohort> getCohortsByName(final String name);

    /**
     * @return all registered cohort or empty list when no cohort is registered
     * @should return all registered cohorts
     * @should return empty list when no cohort is registered
     */
    List<Cohort> getAllCohorts();

    void deleteCohort(final Cohort cohort);

    void deleteAllCohorts();
}
