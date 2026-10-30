package rw.ac.auca.fuelconsumptiontracker.domain;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int routeNumber;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID value) {
        this.id = value;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int value) {
        this.routeNumber = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }
}
