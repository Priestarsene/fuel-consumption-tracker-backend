package rw.ac.auca.fuelconsumptiontracker.service;

import java.util.*;
import org.springframework.stereotype.Service;
import rw.ac.auca.fuelconsumptiontracker.domain.Vehicle;
import rw.ac.auca.fuelconsumptiontracker.repository.VehicleRepository;

@Service
public class VehicleService {
 private final VehicleRepository repo;

 public VehicleService(VehicleRepository repo) {
     this.repo = repo; 
    
    }
 public Vehicle save(Vehicle item) { return repo.save(item); }
 public List<Vehicle> all() {
     return repo.findAll(); 
    
    }

 public Optional<Vehicle> byId(UUID id) {
    
    return repo.findById(id); 

}
 public Optional<Vehicle> update(UUID id, Vehicle item) {

  Optional<Vehicle> old = repo.findById(id);
  if (old.isEmpty()) return Optional.empty();
  item.setId(id);
  return Optional.of(repo.save(item));

 }

 public boolean delete(UUID id) {
  if (!repo.existsById(id)) return false;
  repo.deleteById(id); return true;
 }

 public List<Vehicle> byDepartment(String department) {
    return repo.findByDepartmentIgnoreCaseOrderByPlateNumberAsc(department);
}

}
