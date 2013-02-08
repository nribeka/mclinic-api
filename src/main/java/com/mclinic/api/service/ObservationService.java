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
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.impl.ObservationServiceImpl;

/**
 * Service handling all operation to the @Observation actor/model
 */
@ImplementedBy(ObservationServiceImpl.class)
public interface ObservationService {

    Observation createObservation(final Observation observation);

    Observation updateObservation(final Observation observation);

    /**
     * @param uuid the observation uuid
     * @return the observation with matching uuid or null when no observation match the uuid.
     * @should return observation with matching uuid
     * @should return null when no observation match the uuid
     */
    Observation getObservationByUuid(final String uuid);

    /**
     * @param patient the patient.
     * @return list of all observations for the patient or empty list when no observation found for the patient.
     * @should return list of all observations for the patient
     * @should return empty list when no observation found for the patient
     */
    List<Observation> getAllObservations(final Patient patient);

    /**
     * @param patient the patient.
     * @param term the search term
     * @return list of all observations with matching search term on the searchable fields or empty list
     * @should return list of all observations with matching search term on the searchable fields
     * @should return empty list when no observation match the search term
     */
    List<Observation> searchObservations(final Patient patient, final String term);

    void deleteObservation(final Observation observation);

    void deleteAllObservations(final Patient patient);
}
