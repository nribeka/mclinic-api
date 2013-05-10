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
import com.mclinic.api.model.Cohort;
import com.mclinic.search.api.model.object.Searchable;

import java.io.IOException;

public class CohortAlgorithm extends BaseOpenmrsAlgorithm {

    /**
     * Implementation of this method will define how the object will be serialized from the String representation.
     *
     * @param json the string representation
     * @return the concrete object
     */
    @Override
    public Searchable deserialize(final String json) throws IOException {
        Cohort cohort = new Cohort();

        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$['uuid']");
        cohort.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$['name']");
        cohort.setName(name);

        return cohort;
    }
}
