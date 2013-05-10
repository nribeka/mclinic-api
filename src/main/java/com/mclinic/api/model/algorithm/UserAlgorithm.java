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
import com.mclinic.api.model.User;
import com.mclinic.search.api.model.object.Searchable;
import com.mclinic.search.api.util.StringUtil;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserAlgorithm extends BaseOpenmrsAlgorithm {

    /**
     * Implementation of this method will define how the object will be serialized from the String representation.
     *
     * @param json the string representation
     * @return the concrete object
     */
    @Override
    public Searchable deserialize(final String json) throws IOException {
        User user = new User();

        Object jsonObject = JsonPath.read(json, "$");

        String uuid = JsonPath.read(jsonObject, "$['uuid']");
        user.setUuid(uuid);

        String givenName = JsonPath.read(jsonObject, "$['personName.givenName']");
        user.setGivenName(givenName);

        String middleName = JsonPath.read(jsonObject, "$['personName.middleName']");
        user.setMiddleName(middleName);

        String familyName = JsonPath.read(jsonObject, "$['personName.familyName']");
        user.setFamilyName(familyName);

        String username;
        username = JsonPath.read(jsonObject, "$['username']");
        if (StringUtil.isEmpty(username))
            username = JsonPath.read(jsonObject, "$['systemId']");
        user.setUsername(username);

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

            user.setPrivileges(privileges);
        }

        Object roleArrayObject = JsonPath.read(jsonObject, "$['roles']");
        if (roleArrayObject instanceof JSONArray) {
            List<Role> roles = new ArrayList<Role>();

            JSONArray roleArray = (JSONArray) roleArrayObject;
            for (Object roleObject : roleArray) {
                Role role = new Role();

                String privilegeUuid = JsonPath.read(roleObject, "$['uuid']");
                role.setUuid(privilegeUuid);

                String privilegeName = JsonPath.read(roleObject, "$['name']");
                role.setName(privilegeName);

                roles.add(role);
            }

            user.setRoles(roles);
        }

        return user;
    }
}
