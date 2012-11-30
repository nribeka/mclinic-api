package com.mclinic.api.model.algorithm;

import com.burkeware.search.api.serialization.Algorithm;
import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.Patient;
import com.mclinic.util.ISO8601;

import java.text.ParseException;

public class PatientAlgorithm implements Algorithm {
    /**
     * Implementation of this method will define how the patient will be de-serialized from the JSON representation.
     *
     *
     * @param json the json representation
     * @return the concrete patient object
     */
    @Override
    public Patient deserialize(final String json) {
        Patient patient = new Patient();

        // get the full json object representation and then pass this around to the next JsonPath.read()
        // this should minimize the time for the subsequent read() call
        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$.uuid");
        patient.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$.person.display");
        patient.setName(name);

        String identifier = JsonPath.read(jsonObject, "$.identifiers[0].identifier");
        patient.setIdentifier(identifier);

        String gender = JsonPath.read(jsonObject, "$.person.gender");
        patient.setGender(gender);

        String birthdate = JsonPath.read(jsonObject, "$.person.birthdate");
        try {
            patient.setBirthdate(ISO8601.toCalendar(birthdate).getTime());
        } catch (ParseException e) {
            System.out.println("Unable to parse date data from json payload.");
        }

        patient.setJson(json);

        return patient;
    }

    /**
     * Implementation of this method will define how the patient will be serialized into the JSON representation.
     *
     * @param patient the patient
     * @return the json representation
     */
    @Override
    public String serialize(final Object object) {
    	Patient patient = (Patient) object;
        return patient.getJson();
    }
}