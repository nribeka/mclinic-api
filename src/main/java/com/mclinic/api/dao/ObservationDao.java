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
package com.mclinic.api.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.ObservationDaoImpl;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;

@ImplementedBy(ObservationDaoImpl.class)
public interface ObservationDao {

    Observation createObservation(final Observation observation);

    Observation updateObservation(final Observation observation);

    Observation getObservationByUuid(final String uuid);

    List<Observation> getAllObservations(final Patient patient);

    void deleteObservation(final Observation observation);

    void deleteAllObservations(final Patient patient);
}
