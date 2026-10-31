package rw.ac.auca.fuelconsumptiontracker.service;

import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Service;
import rw.ac.auca.fuelconsumptiontracker.domain.*;
import rw.ac.auca.fuelconsumptiontracker.repository.*;

@Service
public class TripRecordService {

    private final TripRecordRepository repo;
    private final RefillRepository refillRepo;

    public TripRecordService(TripRecordRepository repo, RefillRepository refillRepo) {
        this.repo = repo;
        this.refillRepo = refillRepo;
    }

    public String save(UUID refillId, TripRecord item) {
        Optional<Refill> refill = refillRepo.findById(refillId);
        if (refill.isEmpty()) return "The refill with that id does not exist";
        if (refill.get().getStatus() != RefillStatus.APPROVED)
            return "Trip records are only for APPROVED refills";
        item.setId(null);
        item.setTripDate(LocalDate.now());
        item.setRefill(refill.get());
        repo.save(item);
        return "Trip record saved successfully";
    }

    public List<TripRecord> vehicleHistory(String plateNumber) {
    return repo.findByVehiclePlate(plateNumber);

    }

    public boolean delete(UUID id) {
    if (!repo.existsById(id)) return false;
    repo.deleteById(id);
    return true;
    
    }

}