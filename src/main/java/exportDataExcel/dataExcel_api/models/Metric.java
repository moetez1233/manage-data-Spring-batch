package exportDataExcel.dataExcel_api.models;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Table
public class Metric {
    @Id
    @GeneratedValue
    private Long id;
    private String nameUsine;
    private String created_at;
    //@Enumerated(EnumType.STRING)
    private String typeMetric;
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

    public String getTypeMetric() {
        return typeMetric;
    }

    public void setTypeMetric(String typeMetric) {
        this.typeMetric = typeMetric;
    }

    public String getNameUsine() {
        return nameUsine;
    }

    public void setNameUsine(String nameUsine) {
        this.nameUsine = nameUsine;
    }

    @Override
    public String toString() {
        return "Metric{" +
                "id=" + id +
                ", nameUsine='" + nameUsine + '\'' +
                ", created_at='" + created_at + '\'' +
                ", typeMetric='" + typeMetric + '\'' +
                ", device='" + device + '\'' +
                ", value=" + value +
                '}';
    }
}
