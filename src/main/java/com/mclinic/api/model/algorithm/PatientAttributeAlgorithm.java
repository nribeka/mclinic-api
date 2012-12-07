package com.mclinic.api.model.algorithm;

import com.burkeware.search.api.serialization.Algorithm;
import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.PatientAttribute;

public class PatientAttributeAlgorithm implements Algorithm {

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

    @Override
    public String serialize(final Object object) {
        PatientAttribute patientAttribute = (PatientAttribute) object;
        return patientAttribute.getJson();
    }
}
