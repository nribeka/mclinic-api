package com.mclinic.api.service;

import com.google.inject.ImplementedBy;
import com.mclinic.api.model.Patient;
import com.mclinic.api.service.impl.PatientServiceImpl;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Service handling all operation to the @{Patient} actor/model
 * <p/>
 * TODO: add ability to search based on lucene like query syntax (merging name and identifier).
 */
@ImplementedBy(PatientServiceImpl.class)
public interface PatientService {

    /**
     * Download a single patient record from the patient rest resource into the local lucene repository.
     *
     * @param uuid the uuid of the patient.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download patient with matching uuid.
     */
    void downloadPatientByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Download all patients with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the patient to be downloaded. When empty, will return all patients available.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should download all patient with partially matched name.
     * @should download all patient when name is empty.
     */
    void downloadPatientsByName(final String name) throws IOException, ParseException;

    /**
     * Get a single patient record from the local repository with matching uuid.
     *
     * @param uuid the patient uuid
     * @return patient with matching uuid or null when no patient match the uuid
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return patient with matching uuid
     * @should return null when no patient match the uuid
     */
    Patient getPatientByUuid(final String uuid) throws IOException, ParseException;

    /**
     * Get patient by the identifier of the patient.
     *
     * @param identifier the patient identifier.
     * @return patient with matching identifier or null when no patient match the identifier.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return patient with matching identifier.
     * @should return null when no patient match the identifier.
     */
    Patient getPatientByIdentifier(final String identifier) throws IOException, ParseException;

    /**
     * Get all saved patients in the local repository.
     *
     * @return all registered patients or empty list when no patient is registered.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return all registered patients.
     * @should return empty list when no patient is registered.
     */
    List<Patient> getAllPatients() throws IOException, ParseException;

    /**
     * Get list of patients with name similar to the search term.
     *
     * @param name the patient name.
     * @return list of all patients with matching name or empty list when no patient match the name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return list of all patients with matching name partially.
     * @should return empty list when no patient match the name.
     */
    List<Patient> getPatientsByName(final String name) throws IOException, ParseException;

    /**
     * Search for patients with matching characteristic on the name or identifier with the search term.
     *
     * @param term the search term.
     * @return list of all patients with matching search term on the searchable fields or empty list.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return list of all patients with matching search term on the searchable fields.
     * @should return empty list when no patient match the search term.
     */
    List<Patient> searchPatients(final String term) throws IOException, ParseException;

    /**
     * Delete a single patient object from the local repository.
     *
     * @param patient the patient object.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should delete the patient object from the local repository.
     */
    void deletePatient(final Patient patient) throws IOException, ParseException;
}
