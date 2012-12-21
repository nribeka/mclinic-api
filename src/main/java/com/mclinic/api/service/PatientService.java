package com.mclinic.api.service;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.impl.PatientServiceImpl;

/**
 * Service handling all operation to the @{Patient} actor/model
 */
@ImplementedBy(PatientServiceImpl.class)
public interface PatientService {

    Patient createPatient(final Patient patient);

    Patient updatePatient(final Patient patient);

    Patient getPatientByIdentifier(final String identifier);

    Patient getPatientByUuid(final String uuid);

    List<Patient> getAllPatients();

    List<Patient> getPatientsByName(final String name);

    void deletePatient(final Patient patient);

    void deleteAllPatients();

}
