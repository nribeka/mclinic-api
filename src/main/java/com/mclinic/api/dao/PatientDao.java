package com.mclinic.api.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.PatientDaoImpl;
import com.mclinic.api.model.Patient;

@ImplementedBy(PatientDaoImpl.class)
public interface PatientDao {

    Patient createPatient(Patient patient);

    Patient updatePatient(Patient patient);

    Patient getPatientByIdentifier(String identifier);

    Patient getPatientByUuid(String uuid);

    List<Patient> getAllPatients();

    void deletePatient(Patient patient);

    void deleteAllPatients();

    List<Patient> getPatientsByName(String name);

}
