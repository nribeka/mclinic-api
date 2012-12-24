package com.mclinic.api.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.ObservationDaoImpl;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;

@ImplementedBy(ObservationDaoImpl.class)
public interface ObservationDao {

    public Observation createObservation(final Observation observation);

    public Observation updateObservation(final Observation observation);

    public Observation getObservationByUuid(final String uuid);

    public List<Observation> getAllObservations(final Patient patient);

    public void deleteObservation(final Observation observation);

    public void deleteAllObservations(final Patient patient);
}
