package rw.ac.auca.fuelconsumptiontracker.domain;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehicle")
public class Vehicle {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private UUID id;
     private String make;
     private String model;
     @Column(unique = true)
     private String plateNumber;
     private String department;

     public UUID getId() {
          return id;
     }

     public void setId(UUID value) {
          this.id = value;
     }

     public String getMake() {
          return make;
     }

     public void setMake(String value) {
          this.make = value;
     }

     public String getModel() {
          return model;
     }

     public void setModel(String value) {
          this.model = value;
     }

     public String getPlateNumber() {
          return plateNumber;
     }

     public void setPlateNumber(String value) {
          this.plateNumber = value;
     }

     public String getDepartment() {
          return department;
     }

     public void setDepartment(String value) {
          this.department = value;
     }
}
