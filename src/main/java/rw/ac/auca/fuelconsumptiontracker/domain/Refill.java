package rw.ac.auca.fuelconsumptiontracker.domain;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "refill")
public class Refill {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private UUID id;
     private LocalDate refillDate;
     @Enumerated(EnumType.STRING)
     private RefillStatus status;
     private double litres;
     private int odometer;
     @ManyToOne
     @JoinColumn(name = "vehicle_id")
     private Vehicle vehicle;
     @ManyToOne
     @JoinColumn(name = "driver_id")
     private Driver driver;

     public UUID getId() {
          return id;
     }

     public void setId(UUID value) {
          this.id = value;
     }

     public LocalDate getRefillDate() {
          return refillDate;
     }

     public void setRefillDate(LocalDate value) {
          this.refillDate = value;
     }

     public RefillStatus getStatus() {
          return status;
     }

     public void setStatus(RefillStatus value) {
          this.status = value;
     }

     public double getLitres() {
          return litres;
     }

     public void setLitres(double value) {
          this.litres = value;
     }

     public int getOdometer() {
          return odometer;
     }

     public void setOdometer(int value) {
          this.odometer = value;
     }

     public Vehicle getVehicle() {
          return vehicle;
     }

     public void setVehicle(Vehicle value) {
          this.vehicle = value;
     }

     public Driver getDriver() {
          return driver;
     }

     public void setDriver(Driver value) {
          this.driver = value;
     }
}
