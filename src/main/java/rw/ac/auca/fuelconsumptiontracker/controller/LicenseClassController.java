package rw.ac.auca.fuelconsumptiontracker.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.fuelconsumptiontracker.domain.LicenseClass;
import rw.ac.auca.fuelconsumptiontracker.service.LicenseClassService;

@RestController
@RequestMapping("/api/license-classes")
public class LicenseClassController {

    private final LicenseClassService service;

    public LicenseClassController(LicenseClassService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LicenseClass item) {
        service.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("License class saved successfully");
    }

    @GetMapping("/all")
    public List<LicenseClass> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byId(@PathVariable("id") UUID id) {
        Optional<LicenseClass> item = service.byId(id);
        if (item.isEmpty())
            return ResponseEntity.status(404).body("The license class with that id does not exist");
        return ResponseEntity.ok(item.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody LicenseClass item) {
        Optional<LicenseClass> saved = service.update(id, item);
        if (saved.isEmpty())
            return ResponseEntity.status(404).body("The license class with that id does not exist");
        return ResponseEntity.ok(saved.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        if (!service.delete(id))
            return ResponseEntity.status(404).body("The license class with that id does not exist");
        return ResponseEntity.ok("License class deleted successfully");
    }
}