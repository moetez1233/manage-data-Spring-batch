package exportDataExcel.dataExcel_api.models;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table
public class Metric {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Timestamp created_at;
    @Enumerated(EnumType.STRING)
    private TypeMetric typeMetric;
    private String device;
    private Double value;

    public Metric() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public TypeMetric getTypeMetric() {
        return typeMetric;
    }

    public void setTypeMetric(TypeMetric typeMetric) {
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
}
