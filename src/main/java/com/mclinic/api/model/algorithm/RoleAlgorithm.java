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
import com.mclinic.api.model.Privilege;
import com.mclinic.api.model.Role;
import com.mclinic.search.api.model.object.Searchable;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoleAlgorithm extends BaseOpenmrsAlgorithm {

    /**
     * Implementation of this method will define how the object will be serialized from the String representation.
     *
     * @param json the string representation
     * @return the concrete object
     */
    @Override
    public Searchable deserialize(final String json) throws IOException {
        Role role = new Role();

        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$['uuid']");
        role.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$['name']");
        role.setName(name);

        Object privilegeArrayObject = JsonPath.read(jsonObject, "$['privileges']");
        if (privilegeArrayObject instanceof JSONArray) {
            List<Privilege> privileges = new ArrayList<Privilege>();

            JSONArray privilegeArray = (JSONArray) privilegeArrayObject;
            for (Object privilegeObject : privilegeArray) {
                Privilege privilege = new Privilege();

                String privilegeUuid = JsonPath.read(privilegeObject, "$['uuid']");
                privilege.setUuid(privilegeUuid);

                String privilegeName = JsonPath.read(privilegeObject, "$['name']");
                privilege.setName(privilegeName);

                privileges.add(privilege);
            }

            role.setPrivileges(privileges);
        }

        return role;
    }

    /**
     * Implementation of this method will define how the object will be de-serialized into the String representation.
     *
     * @param object the object
     * @return the string representation
     */
    @Override
    public String serialize(final Searchable object) throws IOException {
        // TODO: Add all other fields into the serialized String.
        // serialize the minimum needed to identify an object for deletion purposes.
        Role role = (Role) object;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", role.getUuid());
        return jsonObject.toJSONString();
    }
}
