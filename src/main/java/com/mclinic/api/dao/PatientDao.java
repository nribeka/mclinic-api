package com.mclinic.api.dao;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.PatientDaoImpl;
import com.mclinic.api.model.Patient;

import java.util.List;

@ImplementedBy (PatientDaoImpl.class)
public interface PatientDao {
	
    Patient createPatient(Patient patient);

    Patient updatePatient(Patient patient);
    
    Patient getPatientByIdentifier(String identifier);
    
    Patient getPatientByUUID(String uuid);

    List<Patient> getAllPatients();

    void deletePatient(Patient patient);

    void deleteAllPatients();

	List<Patient> getPatientsByName(String name);

}
