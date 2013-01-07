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
package com.mclinic.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.mclinic.api.dao.ObservationDao;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.resource.Resource;
import com.mclinic.search.api.util.StringUtil;
import com.mclinic.util.Constants;

public class ObservationDaoImpl implements ObservationDao {

    @Inject
    private Logger log;

    @Inject
    private RestAssuredService service;

    private static final String TAG = ObservationDao.class.getSimpleName();

    @Override
    public Observation createObservation(final Observation observation) {
        Object object = null;
        try {
            Resource resource = Context.getResource(Constants.OBSERVATION_RESOURCE);
            object = service.createObject(observation, resource);
        } catch (Exception e) {
            log.error(TAG, "Error creating observation.", e);
        }
        return (Observation) object;
    }

    @Override
    public Observation updateObservation(final Observation observation) {
        Object object = null;
        try {
            Resource resource = Context.getResource(Constants.OBSERVATION_RESOURCE);
            object = service.updateObject(observation, resource);
        } catch (Exception e) {
            log.error(TAG, "Error updating observation.", e);
        }
        return (Observation) object;
    }

    @Override
    public Observation getObservationByUuid(final String uuid) {
        String searchQuery = StringUtil.EMPTY;
        if (!StringUtil.isEmpty(uuid))
            searchQuery = "uuid: " + StringUtil.quote(uuid);

        Observation observation = null;
        try {
            observation = service.getObject(searchQuery, Observation.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting observation using query: " + searchQuery, e);
        }
        return observation;
    }

    @Override
    public List<Observation> getAllObservations(final Patient patient) {
        String searchQuery = StringUtil.EMPTY;
        if (patient != null && !StringUtil.isEmpty(patient.getUuid()))
            searchQuery = "patient: " + StringUtil.quote(patient.getUuid());

        List<Observation> observations = new ArrayList<Observation>();
        try {
            observations = service.getObjects(searchQuery, Observation.class);
        } catch (Exception e) {
            log.error(TAG, "Error getting observations using query: " + searchQuery, e);
        }
        return observations;
    }

    @Override
    public void deleteObservation(final Observation observation) {
        try {
            Resource resource = Context.getResource(Constants.OBSERVATION_RESOURCE);
            service.invalidate(observation, resource);
        } catch (Exception e) {
            log.error(TAG, "Error deleting observation.", e);
        }
    }

    @Override
    public void deleteAllObservations(final Patient patient) {
        // TODO Do we need to implement the delete all observations?
    }
}