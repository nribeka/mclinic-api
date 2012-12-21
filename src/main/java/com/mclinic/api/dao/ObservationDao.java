package com.mclinic.api.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.ObservationDaoImpl;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;

@ImplementedBy(ObservationDaoImpl.class)
public interface ObservationDao {

    public Observation createObservation(Observation observation);

    public Observation updateObservation(Observation observation);

    public Observation getObservationByUuid(String uuid);

    public List<Observation> getAllObservations(Patient patient);

    public void deleteObservation(Observation observation);

    public void deleteAllObservations(Patient patient);
}
