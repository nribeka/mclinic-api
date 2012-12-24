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

    void deleteObservation(final Observation observation);

    void deleteAllObservations(final Patient patient);
}
