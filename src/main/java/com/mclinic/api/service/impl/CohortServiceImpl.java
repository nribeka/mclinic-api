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
package com.mclinic.api.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.CohortDao;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.service.CohortService;

public class CohortServiceImpl implements CohortService {

    @Inject
    private CohortDao dao;

    @Override
    public Cohort createCohort(final Cohort cohort) {
        return dao.createCohort(cohort);
    }

    @Override
    public Cohort updateCohort(final Cohort cohort) {
        return dao.updateCohort(cohort);
    }

    @Override
    public Cohort getCohortByUuid(final String uuid) {
        return dao.getCohortByUuid(uuid);
    }

    @Override
    public List<Cohort> getCohortsByName(final String name) {
        return dao.getCohortsByName(name);
    }

    @Override
    public List<Cohort> getAllCohorts() {
        return dao.getAllCohorts();
    }

    @Override
    public void deleteCohort(final Cohort cohort) {
        dao.deleteCohort(cohort);
    }

    @Override
    public void deleteAllCohorts() {
        dao.deleteAllCohorts();
    }
}
