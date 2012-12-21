package com.mclinic.api.model.algorithm;

import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.PatientAttribute;
import com.mclinic.search.api.serialization.Algorithm;

public class PatientAttributeAlgorithm implements Algorithm {

    /**
     * Implementation of this method will define how the observation will be serialized from the JSON representation.
     *
     * @param json the json representation
     * @return the concrete observation object
     */
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

    /**
     * Implementation of this method will define how the observation will be de-serialized into the JSON representation.
     *
     * @param object the observation
     * @return the json representation
     */
    @Override
    public String serialize(final Object object) {

        PatientAttribute patientAttribute = (PatientAttribute) object;
        return patientAttribute.getJson();
    }
}
