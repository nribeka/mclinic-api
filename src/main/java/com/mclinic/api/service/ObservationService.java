package com.mclinic.api.service;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.impl.ObservationServiceImpl;

import java.util.List;

/**
 * Service handling all operation to the @Observation actor/model
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
@ImplementedBy (ObservationServiceImpl.class)
public interface ObservationService {
	
    public Observation createObservation(Observation observation);

    public Observation updateObservation(Observation observation);
    
    public Observation getObservationByUUID(String uuid);

    public List<Observation> getAllObservations(Patient patient);

    public void deleteObservation (Observation observation);

    public void deleteAllObservations(Patient patient);
}
