/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package com.mclinic.api.model.algorithm;

import java.text.ParseException;

import com.burkeware.search.api.serialization.Algorithm;
import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.Observation;
import com.mclinic.util.ISO8601;
import net.minidev.json.JSONObject;

public class ObservationAlgorithm implements Algorithm {
    /**
     * Implementation of this method will define how the patient will be serialized from the JSON representation.
     *
     * @param json the json representation
     * @return the concrete observation object
     */
    @Override
    public Observation deserialize(final String json) {
        Observation observation = new Observation();

        // get the full json object representation and then pass this around to the next JsonPath.read()
        // this should minimize the time for the subsequent read() call
        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$.uuid");
        observation.setUuid(uuid);

        String patientUuid = JsonPath.read(jsonObject, "$.person.uuid");
        observation.setPatientUuid(patientUuid);

        String conceptName = JsonPath.read(jsonObject, "$.concept.display");
        observation.setFieldName(conceptName);

        String conceptUuid = JsonPath.read(jsonObject, "$.concept.uuid");
        observation.setFieldUuid(conceptUuid);

        Object jsonValue = JsonPath.read(jsonObject, "$.value");
        if (jsonValue != null) {
            String value = jsonValue.toString();
            if (jsonValue instanceof JSONObject)
                value = JsonPath.read(jsonValue, "$.name.display");
            observation.setValueText(value);
        }

        String obsDatetime = JsonPath.read(jsonObject, "$.obsDatetime");
        try {
            observation.setObservationDate(ISO8601.toCalendar(obsDatetime).getTime());
        } catch (ParseException e) {
            System.out.println("Unable to parse date data from json payload.");
        }

        observation.setJson(json);

        return observation;
    }

    /**
     * Implementation of this method will define how the patient will be deserialized into the JSON representation.
     *
     * @param observation the observation
     * @return the json representation
     */
    @Override
    public String serialize(final Object object) {
        Observation observation = (Observation) object;
        return observation.getJson();
    }
}
