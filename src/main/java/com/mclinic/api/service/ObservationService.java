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

    public Observation createObservation(final Observation observation);

    public Observation updateObservation(final Observation observation);

    public Observation getObservationByUuid(final String uuid);

    public List<Observation> getAllObservations(final Patient patient);

    public void deleteObservation(final Observation observation);

    public void deleteAllObservations(final Patient patient);
}
