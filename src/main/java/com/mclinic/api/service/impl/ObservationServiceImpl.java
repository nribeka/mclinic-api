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
import com.mclinic.api.dao.ObservationDao;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.ObservationService;

public class ObservationServiceImpl implements ObservationService {

    @Inject
    private ObservationDao dao;

    @Override
    public Observation createObservation(final Observation observation) {
        return dao.createObservation(observation);
    }

    @Override
    public Observation updateObservation(final Observation observation) {
        return dao.updateObservation(observation);
    }

    @Override
    public Observation getObservationByUuid(final String uuid) {
        return dao.getObservationByUuid(uuid);
    }

    @Override
    public List<Observation> getAllObservations(final Patient patient) {
        return dao.getAllObservations(patient);
    }

    @Override
    public List<Observation> searchObservations(final Patient patient, final String term) {
        return dao.searchObservations(patient, term);
    }

    @Override
    public void deleteObservation(final Observation observation) {
        dao.deleteObservation(observation);
    }

    @Override
    public void deleteAllObservations(final Patient patient) {
        dao.deleteAllObservations(patient);
    }
}
