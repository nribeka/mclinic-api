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
import com.mclinic.api.model.Observation;
import com.mclinic.search.api.serialization.Algorithm;
import com.mclinic.search.api.util.ISO8601Util;
import net.minidev.json.JSONObject;

public class ObservationAlgorithm implements Algorithm {

    /**
     * Implementation of this method will define how the observation will be serialized from the JSON representation.
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

        String obsValue = JsonPath.read(jsonObject, "$.display");
        // extract the observation value information
        int index = obsValue.indexOf("=");
        if (index != -1)
            obsValue = obsValue.substring(index + 1);
        observation.setValueText(obsValue.trim());

        String obsDatetime = JsonPath.read(jsonObject, "$.obsDatetime");
        try {
            observation.setObservationDate(ISO8601Util.toCalendar(obsDatetime).getTime());
        } catch (ParseException e) {
            System.out.println("Unable to parse date data from json payload.");
        }

        observation.setJson(json);

        return observation;
    }

    /**
     * Implementation of this method will define how the observation will be de-serialized into the JSON representation.
     *
     * @param object the observation
     * @return the json representation
     */
    @Override
    public String serialize(final Object object) {
        Observation observation = (Observation) object;
        // TODO: need to replace this json with values from the new user object in case there's any update
        // Step:
        // - Execute JsonPath.read to get the current value
        // - Perform StringUtil.replace to replace the old value with the value from the object
        // - Unique id are not allowed to get any kind of update.
        return observation.getJson();
    }
}
