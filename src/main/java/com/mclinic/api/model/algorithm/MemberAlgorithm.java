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
import com.mclinic.api.model.Member;
import com.mclinic.search.api.model.object.Searchable;

import java.io.IOException;

public class MemberAlgorithm extends BaseOpenmrsAlgorithm {

    /**
     * Implementation of this method will define how the patient will be serialized from the JSON representation.
     *
     * @param serialized the json representation
     * @return the concrete patient object
     */
    @Override
    public Searchable deserialize(final String serialized) throws IOException {
        Member member = new Member();

        Object jsonObject = JsonPath.read(serialized, "$");

        String userUuid = JsonPath.read(jsonObject, "$['cohort.uuid']");
        member.setCohortUuid(userUuid);

        String patientUuid = JsonPath.read(jsonObject, "$['patient.uuid']");
        member.setPatientUuid(patientUuid);

        return member;
    }
}
