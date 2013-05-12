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
import com.mclinic.api.model.Form;
import com.mclinic.search.api.model.object.Searchable;
import net.minidev.json.JSONObject;

import java.io.IOException;

public class FormAlgorithm extends BaseOpenmrsAlgorithm {

    /**
     * Implementation of this method will define how the observation will be serialized from the JSON representation.
     *
     * @param json the json representation
     * @return the concrete observation object
     */
    @Override
    public Searchable deserialize(final String json) throws IOException {

        Form form = new Form();

        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$['uuid']");
        form.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$['name']");
        form.setName(name);

        return form;
    }

    /**
     * Implementation of this method will define how the object will be de-serialized into the String representation.
     *
     * @param object the object
     * @return the string representation
     */
    @Override
    public String serialize(final Searchable object) throws IOException {
        // serialize the minimum needed to identify an object for deletion purposes.
        Form form = (Form) object;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", form.getUuid());
        jsonObject.put("name", form.getUuid());
        return jsonObject.toJSONString();
    }
}
