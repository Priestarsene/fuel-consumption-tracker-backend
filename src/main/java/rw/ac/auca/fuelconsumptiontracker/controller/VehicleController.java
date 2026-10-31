package rw.ac.auca.fuelconsumptiontracker.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.fuelconsumptiontracker.domain.Vehicle;
import rw.ac.auca.fuelconsumptiontracker.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")

public class VehicleController {
 private final VehicleService service;
 public VehicleController(VehicleService service) {

     this.service = service; 
    
    }


 @PostMapping("/save")
 public ResponseEntity<String> save(@RequestBody Vehicle item) {

  service.save(item);
  return ResponseEntity.status(HttpStatus.CREATED).body("Vehicle saved successfully");

 }


 @GetMapping("/all")
 public List<Vehicle> all() {

     return service.all(); 
    }


 @GetMapping("/{id}")
 public ResponseEntity<?> byId(@PathVariable("id") UUID id) {
  Optional<Vehicle> item = service.byId(id);
  if (item.isEmpty()) return ResponseEntity.status(404).body("The vehicle with that id does not exist");
  return ResponseEntity.ok(item.get());
 }

 @GetMapping("/by-department")
public List<Vehicle> byDepartment(@RequestParam("department") String department) {
    return service.byDepartment(department);
}


 @PutMapping("/update/{id}")
 public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Vehicle item) {
  Optional<Vehicle> saved = service.update(id,item);
  if (saved.isEmpty()) return ResponseEntity.status(404).body("The vehicle with that id does not exist");
  return ResponseEntity.ok(saved.get());
 }


 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
  if (!service.delete(id)) return ResponseEntity.status(404).body("The vehicle with that id does not exist");
  return ResponseEntity.ok("Vehicle deleted successfully");
 }


}
