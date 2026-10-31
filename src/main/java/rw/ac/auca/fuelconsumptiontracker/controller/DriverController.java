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


@GetMapping("/by-license-class")
public List<Driver> byLicenseClass(@RequestParam("name") String name) {
    return service.byLicenseClass(name);

}

 @PutMapping("/update/{id}")
 public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Driver item) {
  Optional<Driver> saved = service.update(id,item);
  if (saved.isEmpty()) return ResponseEntity.status(404).body("The driver with that id does not exist");
  return ResponseEntity.ok(saved.get());
 }

 @PutMapping("/{driverId}/license-classes/{licenseClassId}")

 public ResponseEntity<String> addLicenseClass(
        @PathVariable("driverId") UUID driverId,
        @PathVariable("licenseClassId") UUID classId) {
    String msg = service.addLicenseClass(driverId, classId);
    if (msg.equals("The driver with that id does not exist") || msg.equals("That license class does not exist"))
        return ResponseEntity.status(404).body(msg);
    if (msg.equals("The driver already has that license class"))
        return ResponseEntity.status(409).body(msg);
    return ResponseEntity.ok(msg);

 }


 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
  if (!service.delete(id)) return ResponseEntity.status(404).body("The driver with that id does not exist");
  return ResponseEntity.ok("Driver deleted successfully");
 }
 
 @PatchMapping("/{driverId}/route")
 
public ResponseEntity<String> changeRoute(
        @PathVariable("driverId") UUID id,
        @RequestParam("routeNumber") int number) {
    String msg = service.changeRoute(id, number);
    if (msg.equals("The driver with that id does not exist") || msg.equals("The route with that routeNumber does not exist"))
        return ResponseEntity.status(404).body(msg);
    if (msg.equals("That route is taken by another driver"))
        return ResponseEntity.status(409).body(msg);
    return ResponseEntity.ok(msg);
 }


}
