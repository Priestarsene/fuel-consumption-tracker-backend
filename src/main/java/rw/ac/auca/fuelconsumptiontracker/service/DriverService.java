package rw.ac.auca.fuelconsumptiontracker.service;

import java.util.*;
import org.springframework.stereotype.Service;
import rw.ac.auca.fuelconsumptiontracker.domain.Driver;
import rw.ac.auca.fuelconsumptiontracker.repository.DriverRepository;


@Service
public class DriverService {
 private final DriverRepository repo;
 public DriverService(DriverRepository repo) {
     this.repo = repo;
     }
     
 public Driver save(Driver item) {
     return repo.save(item);
     }

 public List<Driver> all() {
     return repo.findAll(); 
    }

 public Optional<Driver> byId(UUID id) {
     return repo.findById(id); 
    }

 public Optional<Driver> update(UUID id, Driver item) {
  Optional<Driver> old = repo.findById(id);
  if (old.isEmpty()) return Optional.empty();
  item.setId(id);
  return Optional.of(repo.save(item));
 }

 public boolean delete(UUID id) {
  if (!repo.existsById(id)) return false;
  repo.deleteById(id); 
  return true;
 }

}
