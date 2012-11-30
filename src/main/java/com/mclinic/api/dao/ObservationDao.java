package com.mclinic.api.dao;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.ObservationDaoImpl;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;

import java.util.List;

@ImplementedBy (ObservationDaoImpl.class)
public interface ObservationDao {
	
    public Observation createObservation(Observation observation);

    public Observation updateObservation(Observation observation);
    
    public Observation getObservationByUUID(String uuid);

    public List<Observation> getAllObservations(Patient patient);

    public void deleteObservation (Observation observation);

    public void deleteAllObservations(Patient patient);
}
