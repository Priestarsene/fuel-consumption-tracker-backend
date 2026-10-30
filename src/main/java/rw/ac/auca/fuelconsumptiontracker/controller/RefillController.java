package rw.ac.auca.fuelconsumptiontracker.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.fuelconsumptiontracker.domain.Refill;
import rw.ac.auca.fuelconsumptiontracker.service.RefillService;


@RestController
@RequestMapping("/api/refills")

public class RefillController {

 private final RefillService service;
 public RefillController(RefillService service) {
     this.service = service;
    
    }

 @PostMapping("/save")
 public ResponseEntity<String> save(@RequestBody Refill item) {

  service.save(item);
  return ResponseEntity.status(HttpStatus.CREATED).body("Refill saved successfully");

 }
 @GetMapping("/all")
 public List<Refill> all() {
    
    return service.all();
 }


 @GetMapping("/{id}")
 public ResponseEntity<?> byId(@PathVariable("id") UUID id) {
  Optional<Refill> item = service.byId(id);
  if (item.isEmpty()) return ResponseEntity.status(404).body("The refill with that id does not exist");
  return ResponseEntity.ok(item.get());
 }


 @PutMapping("/update/{id}")
 public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Refill item) {
  Optional<Refill> saved = service.update(id,item);
  if (saved.isEmpty()) return ResponseEntity.status(404).body("The refill with that id does not exist");
  return ResponseEntity.ok(saved.get());
 }

 
 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
  if (!service.delete(id)) return ResponseEntity.status(404).body("The refill with that id does not exist");
  return ResponseEntity.ok("Refill deleted successfully");
 }
}
