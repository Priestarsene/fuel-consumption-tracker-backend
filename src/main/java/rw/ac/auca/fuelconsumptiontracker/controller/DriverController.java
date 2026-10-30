package rw.ac.auca.fuelconsumptiontracker.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.fuelconsumptiontracker.domain.Driver;
import rw.ac.auca.fuelconsumptiontracker.service.DriverService;


@RestController
@RequestMapping("/api/drivers")

public class DriverController {
 private final DriverService service;
 public DriverController(DriverService service) {
    
    this.service = service; 
    
    }
    
 @PostMapping("/save")
 public ResponseEntity<String> save(@RequestBody Driver item) {
  service.save(item);
  return ResponseEntity.status(HttpStatus.CREATED).body("Driver saved successfully");
 }


 @GetMapping("/all")
 public List<Driver> all() {
     return service.all(); 
    
    }


 @GetMapping("/{id}")
 public ResponseEntity<?> byId(@PathVariable("id") UUID id) {
  Optional<Driver> item = service.byId(id);
  if (item.isEmpty()) return ResponseEntity.status(404).body("The driver with that id does not exist");
  return ResponseEntity.ok(item.get());
 }


 @PutMapping("/update/{id}")
 public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Driver item) {
  Optional<Driver> saved = service.update(id,item);
  if (saved.isEmpty()) return ResponseEntity.status(404).body("The driver with that id does not exist");
  return ResponseEntity.ok(saved.get());
 }


 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
  if (!service.delete(id)) return ResponseEntity.status(404).body("The driver with that id does not exist");
  return ResponseEntity.ok("Driver deleted successfully");
 }

}
