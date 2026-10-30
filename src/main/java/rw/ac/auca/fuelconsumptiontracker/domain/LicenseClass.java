package rw.ac.auca.fuelconsumptiontracker.domain;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "license_class")
public class LicenseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "licenseClasses")
    @JsonIgnore
    private List<Driver> drivers = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> value) {
        this.drivers = value;
    }
}
