package com.mclinic.api.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.dao.impl.PatientDaoImpl;
import com.mclinic.api.model.Patient;

@ImplementedBy(PatientDaoImpl.class)
public interface PatientDao {

    Patient createPatient(final Patient patient);

    Patient updatePatient(final Patient patient);

    Patient getPatientByUuid(final String uuid);

    Patient getPatientByIdentifier(final String identifier);

    List<Patient> getAllPatients();

    List<Patient> getPatientsByName(final String name);

    void deletePatient(final Patient patient);

    void deleteAllPatients();

}
