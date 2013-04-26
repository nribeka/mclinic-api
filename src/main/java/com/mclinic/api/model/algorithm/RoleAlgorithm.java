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
import com.mclinic.api.model.Role;
import com.mclinic.search.api.model.object.Searchable;
import com.mclinic.search.api.util.DigestUtil;

import java.io.IOException;

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

        String uuid = JsonPath.read(jsonObject, "$.uuid");
        role.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$.display");
        role.setName(name);

        String checksum = DigestUtil.getSHA1Checksum(json);
        role.setChecksum(checksum);

        String uri = JsonPath.read(jsonObject, "$.links[0].uri");
        role.setUri(uri);

        return role;
    }
}
