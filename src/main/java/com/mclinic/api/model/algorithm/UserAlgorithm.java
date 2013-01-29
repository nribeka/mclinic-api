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
import com.mclinic.api.model.User;
import com.mclinic.search.api.serialization.Algorithm;
import com.mclinic.search.api.util.StringUtil;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class UserAlgorithm implements Algorithm {

    /**
     * Implementation of this method will define how the object will be serialized from the String representation.
     *
     * @param json the string representation
     * @return the concrete object
     */
    @Override
    public Object deserialize(final String json) {
        User user = new User();

        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$.uuid");
        user.setUuid(uuid);

        String display = JsonPath.read(jsonObject, "$.display");
        String[] displayElements = StringUtil.split(display, "-");
        user.setUsername(displayElements[0]);
        user.setName(displayElements[1]);

        try {
            String password = JsonPath.read(json, "$.password");
            user.setPassword(password);
            String salt = JsonPath.read(json, "$.salt");
            user.setSalt(salt);
        } catch (Exception e) {
            // TODO: damn this is totally bogus!!!
        }

        user.setJson(json);

        return user;
    }

    /**
     * Implementation of this method will define how the object will be de-serialized into the String representation.
     *
     * @param object the object
     * @return the string representation
     */
    @Override
    public String serialize(final Object object) {
        User user = (User) object;
        String json = user.getJson();

        String display = JsonPath.read(json, "$.display");
        if (!StringUtil.isEmpty(display))
            json = json.replaceAll(display, user.getUsername() + " - " + user.getName());

        JSONObject jsonObject = JsonPath.read(json, "$");
        jsonObject.put("password", user.getPassword());
        jsonObject.put("salt", user.getSalt());
        return json;
    }
}
