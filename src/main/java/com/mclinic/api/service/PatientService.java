package com.mclinic.api.service;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.impl.PatientServiceImpl;

import java.util.List;

/**
 * Service handling all operation to the @{Patient} actor/model
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
@ImplementedBy (PatientServiceImpl.class)
public interface PatientService {
	
    Patient createPatient(Patient patient);

    Patient updatePatient(Patient patient);
    
    Patient getPatientByIdentifier(String identifier);
    
    Patient getPatientByUUID(String uuid);

    List<Patient> getAllPatients();
    
    List<Patient> getPatientsByName(String name);

    void deletePatient(Patient patient);

    void deleteAllPatients();

}
