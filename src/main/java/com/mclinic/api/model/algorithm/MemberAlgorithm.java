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
import com.mclinic.search.api.model.serialization.BaseAlgorithm;
import com.mclinic.search.api.util.DigestUtil;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.IOException;

public class MemberAlgorithm extends BaseAlgorithm {

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

        String uuid = JsonPath.read(jsonObject, "$.uuid");
        member.setUuid(uuid);

        String userUuid = JsonPath.read(jsonObject, "$.cohortUuid");
        member.setCohortUuid(userUuid);

        String checksum = DigestUtil.getSHA1Checksum(serialized);
        member.setChecksum(checksum);

        Object jsonMembers = JsonPath.read(jsonObject, "$.patients");
        if (jsonMembers instanceof JSONArray) {
            for (Object jsonMember : (JSONArray) jsonMembers) {
                String patientUuid = JsonPath.read(jsonMember, "$.uuid");
                member.addPatient(patientUuid);
            }
        }

        return member;
    }

    /**
     * Implementation of this method will define how the object will be de-serialized into the String representation.
     *
     * @param object the object
     * @return the string representation
     */
    @Override
    public String serialize(final Searchable object) throws IOException {
        Member member = (Member) object;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", member.getUuid());
        jsonObject.put("userUuid", member.getCohortUuid());
        JSONArray patients = new JSONArray();
        for (String patientUuid : member.getPatients()) {
            JSONObject patientObject = new JSONObject();
            patientObject.put("uuid", patientUuid);
            patients.add(patientObject);
        }
        jsonObject.put("patients", patients);
        return jsonObject.toJSONString();
    }
}
