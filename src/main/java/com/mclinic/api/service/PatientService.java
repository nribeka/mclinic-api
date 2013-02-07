package com.mclinic.api.service;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Observation;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.impl.PatientServiceImpl;

/**
 * Service handling all operation to the @{Patient} actor/model
 *
 * TODO: add ability to search based on lucene like query syntax (merging name and identifier).
 */
@ImplementedBy(PatientServiceImpl.class)
public interface PatientService {

    Patient createPatient(final Patient patient);

    Patient updatePatient(final Patient patient);

    /**
     * @param uuid the patient uuid
     * @return patient with matching uuid or null when no patient match the uuid
     * @should return patient with matching uuid
     * @should return null when no patient match the uuid
     */
    Patient getPatientByUuid(final String uuid);

    /**
     * @param identifier the patient identifier
     * @return patient with matching identifier or null when no patient match the identifier
     * @should return patient with matching identifier
     * @should return null when no patient match the identifier
     */
    Patient getPatientByIdentifier(final String identifier);

    /**
     * @return all registered patients or empty list when no patient is registered
     * @should return all registered patients
     * @should return empty list when no patient is registered
     */
    List<Patient> getAllPatients();

    /**
     * @param name the patient name
     * @return list of all patients with matching name or empty list when no patient match the name
     * @should return list of all patients with matching name
     * @should return empty list when no patient match the name
     */
    List<Patient> getPatientsByName(final String name);

    /**
     * @param term the search term
     * @return list of all patients with matching search term on the searchable fields or empty list
     * @should return list of all patients with matching search term on the searchable fields
     * @should return empty list when no patient match the search term
     */
    List<Patient> searchPatients(final String term);

    void deletePatient(final Patient patient);

    void deleteAllPatients();

}
