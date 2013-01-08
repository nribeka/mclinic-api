/**
 * Copyright 2012 Muzima Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mclinic.api.model.algorithm;

import java.text.ParseException;

import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.Patient;
import com.mclinic.search.api.serialization.Algorithm;
import com.mclinic.search.api.util.ISO8601Util;

public class PatientAlgorithm implements Algorithm {

    /**
     * Implementation of this method will define how the observation will be serialized from the JSON representation.
     *
     * @param json the json representation
     * @return the concrete observation object
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
            patient.setBirthdate(ISO8601Util.toCalendar(birthdate).getTime());
        } catch (ParseException e) {
            System.out.println("Unable to parse date data from json payload.");
        }

        patient.setJson(json);

        return patient;
    }

    /**
     * Implementation of this method will define how the observation will be de-serialized into the JSON representation.
     *
     * @param object the observation
     * @return the json representation
     */
    @Override
    public String serialize(final Object object) {

        Patient patient = (Patient) object;
        return patient.getJson();
    }
}
