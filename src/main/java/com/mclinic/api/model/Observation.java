package com.mclinic.api.model;

import java.util.Date;

import com.mclinic.search.api.util.StringUtil;

public class Observation {

    private String uuid;

    private String patientUuid;

    private String valueText = StringUtil.EMPTY;

    private Date observationDate;

    private String fieldName;

    private String fieldUuid;

    private byte dataType;

    private String json;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public String getPatientUuid() {
        return patientUuid;
    }

    public void setPatientUuid(final String patientUuid) {
        this.patientUuid = patientUuid;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(final String valueText) {
        this.valueText = valueText;
    }

    public Date getObservationDate() {
        return observationDate;
    }

    public void setObservationDate(final Date observationDate) {
        this.observationDate = observationDate;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldUuid() {
        return fieldUuid;
    }

    public void setFieldUuid(final String fieldUuid) {
        this.fieldUuid = fieldUuid;
    }

    public byte getDataType() {
        return dataType;
    }

    public void setDataType(byte dataType) {
        this.dataType = dataType;
    }

    public String getJson() {
        return json;
    }

    public void setJson(final String json) {
        this.json = json;
    }
}
