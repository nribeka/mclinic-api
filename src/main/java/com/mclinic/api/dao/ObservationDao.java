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
