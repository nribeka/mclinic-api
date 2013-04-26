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

import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.Credential;
import com.mclinic.search.api.model.object.Searchable;
import com.mclinic.search.api.model.serialization.BaseAlgorithm;
import com.mclinic.search.api.util.DigestUtil;
import net.minidev.json.JSONObject;

import java.io.IOException;

public class CredentialAlgorithm extends BaseAlgorithm {

    /**
     * Implementation of this method will define how the object will be serialized from the String representation.
     *
     * @param json the string representation
     * @return the concrete object
     */
    @Override
    public Searchable deserialize(final String json) throws IOException {
        Credential user = new Credential();

        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$.uuid");
        user.setUuid(uuid);

        String userUuid = JsonPath.read(jsonObject, "$.userUuid");
        user.setUserUuid(userUuid);

        String username = JsonPath.read(jsonObject, "$.username");
        user.setUsername(username);

        String password = JsonPath.read(jsonObject, "$.password");
        user.setPassword(password);

        String seed = JsonPath.read(jsonObject, "$.seed");
        user.setSeed(seed);

        String checksum = DigestUtil.getSHA1Checksum(json);
        user.setChecksum(checksum);

        return user;
    }

    /**
     * Implementation of this method will define how the object will be de-serialized into the String representation.
     *
     * @param object the object
     * @return the string representation
     */
    @Override
    public String serialize(final Searchable object) throws IOException {
        Credential credential = (Credential) object;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", credential.getUuid());
        jsonObject.put("userUuid", credential.getUserUuid());
        jsonObject.put("username", credential.getUsername());
        jsonObject.put("password", credential.getPassword());
        jsonObject.put("seed", credential.getSeed());
        return jsonObject.toJSONString();
    }
}
