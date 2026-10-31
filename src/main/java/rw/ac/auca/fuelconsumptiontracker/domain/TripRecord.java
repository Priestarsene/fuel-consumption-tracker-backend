package rw.ac.auca.fuelconsumptiontracker.domain;

import jakarta.persistence.*;
import java.util.*;
import java.time.LocalDate;

@Entity
@Table(name = "trip_record")
public class TripRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String destination;
    private String notes;
    private int distanceKm;
    private LocalDate tripDate;

    @ManyToOne
    @JoinColumn(name = "refill_id")
    private Refill refill;

    public UUID getId() { return id; }
    public void setId(UUID x) { id = x; }
    public String getDestination() { return destination; }
    public void setDestination(String x) { destination = x; }
    public String getNotes() { return notes; }
    public void setNotes(String x) { notes = x; }
    public int getDistanceKm() { return distanceKm; }
    public void setDistanceKm(int x) { distanceKm = x; }
    public LocalDate getTripDate() { return tripDate; }
    public void setTripDate(LocalDate x) { tripDate = x; }
    public Refill getRefill() { return refill; }
    public void setRefill(Refill x) { refill = x; }
}