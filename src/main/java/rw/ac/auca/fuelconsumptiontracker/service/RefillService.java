package rw.ac.auca.fuelconsumptiontracker.service;

import java.util.*;
import org.springframework.stereotype.Service;
import rw.ac.auca.fuelconsumptiontracker.domain.Refill;
import rw.ac.auca.fuelconsumptiontracker.repository.RefillRepository;
import rw.ac.auca.fuelconsumptiontracker.domain.RefillStatus;
import java.time.LocalDate;
import rw.ac.auca.fuelconsumptiontracker.repository.TripRecordRepository;
import org.springframework.transaction.annotation.Transactional;



@Service
public class RefillService {

 private final RefillRepository repo;
 private final TripRecordRepository tripRecordRepository;

 public RefillService(RefillRepository repo, TripRecordRepository tripRecordRepository) {
    this.repo = repo;
    this.tripRecordRepository = tripRecordRepository;
 }


 public String save(Refill item) {

    if (item.getRefillDate() == null || item.getRefillDate().isBefore(LocalDate.now())) {
        return "Refill date cannot be in the past";
    }

    if (repo.existsByDriverIdAndRefillDateAndStatusNot(
            item.getDriver().getId(), item.getRefillDate(), RefillStatus.REJECTED)) {
        return "Driver already has a refill on that date";
    }

    item.setStatus(RefillStatus.LOGGED);
    repo.save(item);
    return "Refill saved successfully";

 }

 public List<Refill> all() {
     return repo.findAll();
    
    }
 public Optional<Refill> byId(UUID id) {
     return repo.findById(id); 
    
    }

 public Optional<Refill> update(UUID id, Refill incoming) {

    Optional<Refill> found = repo.findById(id);
    if (found.isEmpty()) return Optional.empty();
    Refill existing = found.get();

    existing.setRefillDate(incoming.getRefillDate());
    existing.setLitres(incoming.getLitres());
    existing.setOdometer(incoming.getOdometer());
    existing.setVehicle(incoming.getVehicle());
    existing.setDriver(incoming.getDriver());
    
    return Optional.of(repo.save(existing));
}

public String deleteWithProtection(UUID id) {

    if (!repo.existsById(id)) return "The refill with that id does not exist";
    if (tripRecordRepository.existsByRefillId(id))
        return "That refill has trip records, delete them first";
    repo.deleteById(id);
    return "Refill deleted successfully";

}

 public List<Refill> byStatus(RefillStatus status) {

    return repo.findByStatusOrderByRefillDateAsc(status);

}

public List<Refill> between(LocalDate start, LocalDate end) {

    return repo.findByRefillDateBetweenOrderByRefillDateAsc(start, end);

}

public String changeStatus(UUID id, RefillStatus next) {

    Optional<Refill> found = repo.findById(id);
    if (found.isEmpty()) return "The refill with that id does not exist";
    Refill item = found.get();
    RefillStatus current = item.getStatus();
    boolean allowed = (current == RefillStatus.LOGGED && (next == RefillStatus.VERIFIED || next == RefillStatus.REJECTED))
        || (current == RefillStatus.VERIFIED && (next == RefillStatus.APPROVED || next == RefillStatus.REJECTED));
    if (!allowed) return "Cannot change status from " + current + " to " + next;
    item.setStatus(next);
    repo.save(item);
    return "Refill status updated successfully";
}

 public List<Object[]> statsByStatus() {
    return repo.countRefillsByStatus();
 }

 @Transactional
 public String cancelDay(UUID driverId, LocalDate date) {
    int count = repo.rejectDriverDay(driverId, date, RefillStatus.REJECTED, RefillStatus.APPROVED);
    return count + " refills cancelled";
}

}
