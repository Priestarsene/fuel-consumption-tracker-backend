package rw.ac.auca.fuelconsumptiontracker.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.fuelconsumptiontracker.domain.Route;
import rw.ac.auca.fuelconsumptiontracker.service.RouteService;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService service;

    public RouteController(RouteService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Route item) {
        service.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Route saved successfully");
    }

    @GetMapping("/all")
    public List<Route> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byId(@PathVariable("id") UUID id) {
        Optional<Route> item = service.byId(id);
        if (item.isEmpty())
            return ResponseEntity.status(404).body("The route with that id does not exist");
        return ResponseEntity.ok(item.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Route item) {
        Optional<Route> saved = service.update(id, item);
        if (saved.isEmpty())
            return ResponseEntity.status(404).body("The route with that id does not exist");
        return ResponseEntity.ok(saved.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        if (!service.delete(id))
            return ResponseEntity.status(404).body("The route with that id does not exist");
        return ResponseEntity.ok("Route deleted successfully");
    }
}