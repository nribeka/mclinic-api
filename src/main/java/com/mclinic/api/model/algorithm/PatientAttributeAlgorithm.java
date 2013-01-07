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
import com.mclinic.api.model.PatientAttribute;
import com.mclinic.search.api.serialization.Algorithm;

public class PatientAttributeAlgorithm implements Algorithm {

    /**
     * Implementation of this method will define how the observation will be serialized from the JSON representation.
     *
     * @param json the json representation
     * @return the concrete observation object
     */
    @Override
    public PatientAttribute deserialize(final String json) {

        PatientAttribute patientAttribute = new PatientAttribute();

        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$.uuid");
        patientAttribute.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$.display");
        patientAttribute.setName(name);

        String value = JsonPath.read(jsonObject, "$.value");
        patientAttribute.setValue(value);

        patientAttribute.setJson(json);

        return patientAttribute;
    }

    /**
     * Implementation of this method will define how the observation will be de-serialized into the JSON representation.
     *
     * @param object the observation
     * @return the json representation
     */
    @Override
    public String serialize(final Object object) {

        PatientAttribute patientAttribute = (PatientAttribute) object;
        return patientAttribute.getJson();
    }
}
