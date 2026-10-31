package rw.ac.auca.fuelconsumptiontracker.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.fuelconsumptiontracker.domain.*;
import rw.ac.auca.fuelconsumptiontracker.service.*;

@RestController
@RequestMapping("/api/trip-records")
public class TripRecordController {

    private final TripRecordService service;

    public TripRecordController(TripRecordService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestParam("refillId") UUID id,
            @RequestBody TripRecord item) {
        String msg = service.save(id, item);
        if (msg.equals("The refill with that id does not exist"))
            return ResponseEntity.status(404).body(msg);
        if (msg.equals("Trip records are only for APPROVED refills"))
            return ResponseEntity.badRequest().body(msg);
        return ResponseEntity.status(201).body(msg);
    }

    @GetMapping("/vehicle/{plateNumber}")
    public List<TripRecord> vehicleHistory(@PathVariable("plateNumber") String plateNumber) {
    return service.vehicleHistory(plateNumber);
    
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
    if (!service.delete(id))
        return ResponseEntity.status(404).body("The trip record with that id does not exist");
    return ResponseEntity.ok("Trip record deleted successfully");

    }
    
}