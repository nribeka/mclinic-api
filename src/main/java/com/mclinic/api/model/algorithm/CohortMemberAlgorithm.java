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

import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.Patient;
import com.mclinic.search.api.serialization.Algorithm;

public class CohortMemberAlgorithm implements Algorithm {

    /**
     * Implementation of this method will define how the patient will be serialized from the JSON representation.
     *
     * @param serialized the json representation
     * @return the concrete patient object
     */
    @Override
    public Patient deserialize(final String serialized) {
        Patient patient = new Patient();

        // get the full json object representation and then pass this around to the next JsonPath.read()
        // this should minimize the time for the subsequent read() call
        Object jsonObject = JsonPath.read(serialized, "$");

        String uuid = JsonPath.read(jsonObject, "$.patient.uuid");
        patient.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$.patient.person.display");
        patient.setName(name);

        // this the example of the identifier entry: "OpenMRS Identification Number = 1984MP-5"
        String identifier = JsonPath.read(jsonObject, "$.patient.identifiers[0].display");
        // extract the identifier value
        int index = identifier.indexOf("-");
        if (index != -1)
            identifier = identifier.substring(index + 1);
        patient.setIdentifier(identifier.trim());

        String gender = JsonPath.read(jsonObject, "$.patient.person.gender");
        patient.setGender(gender);

        patient.setJson(serialized);

        return patient;
    }

    /**
     * Implementation of this method will define how the object will be de-serialized into the String representation.
     *
     * @param object the object
     * @return the string representation
     */
    @Override
    public String serialize(final Object object) {
        Patient patient = (Patient) object;
        // TODO: need to replace this json with values from the new user object in case there's any update
        // Step:
        // - Execute JsonPath.read to get the current value
        // - Perform StringUtil.replace to replace the old value with the value from the object
        // - Unique id are not allowed to get any kind of update.
        return patient.getJson();
    }
}
