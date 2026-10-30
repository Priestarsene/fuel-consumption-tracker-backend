package rw.ac.auca.fuelconsumptiontracker.domain;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "driver")
public class Driver {

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private UUID id;
     private String firstName;
     private String lastName;
     private String licenseNumber;

     @ManyToOne
     @JoinColumn(name = "route_id")
     private Route route;
     
     @ManyToMany
     @JoinTable(name = "driver_license_class", joinColumns = @JoinColumn(name = "driver_id"), inverseJoinColumns = @JoinColumn(name = "license_class_id"))
     private List<LicenseClass> licenseClasses = new ArrayList<>();

     public UUID getId() {
          return id;
     }

     public void setId(UUID value) {
          this.id = value;
     }

     public String getFirstName() {
          return firstName;
     }

     public void setFirstName(String value) {
          this.firstName = value;
     }

     public String getLastName() {
          return lastName;
     }

     public void setLastName(String value) {
          this.lastName = value;
     }

     public String getLicenseNumber() {
          return licenseNumber;
     }

     public void setLicenseNumber(String value) {
          this.licenseNumber = value;
     }

     public Route getRoute() {
          return route;
     }

     public void setRoute(Route value) {
          this.route = value;
     }

     public List<LicenseClass> getLicenseClasses() {
          return licenseClasses;
     }

     public void setLicenseClasses(List<LicenseClass> value) {
          this.licenseClasses = value;
     }
}
