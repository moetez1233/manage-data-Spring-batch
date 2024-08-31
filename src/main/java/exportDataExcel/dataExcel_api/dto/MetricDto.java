package exportDataExcel.dataExcel_api.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.security.Timestamp;
import java.util.Date;

public class MetricDto{
    private String nameUsine;
    private String typeMetric;
    private String device;
    private Double value;
    private String created_at;

    public MetricDto() {
    }


    public String getTypeMetric() {
        return typeMetric;
    }

    public void setTypeMetric(String typeMetric) {
        this.typeMetric = typeMetric;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNameUsine() {
        return nameUsine;
    }

    public void setNameUsine(String nameUsine) {
        this.nameUsine = nameUsine;
    }
}
