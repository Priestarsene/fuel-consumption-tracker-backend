package rw.ac.auca.fuelconsumptiontracker.service;

import java.util.*;
import org.springframework.stereotype.Service;
import rw.ac.auca.fuelconsumptiontracker.domain.Refill;
import rw.ac.auca.fuelconsumptiontracker.repository.RefillRepository;

@Service
public class RefillService {
 private final RefillRepository repo;

 public RefillService(RefillRepository repo) {
    
    this.repo = repo; 

 }
 public Refill save(Refill item) {
    
    return repo.save(item); 
 }


 public List<Refill> all() {
     return repo.findAll();
    
    }
 public Optional<Refill> byId(UUID id) {
     return repo.findById(id); 
    
    }
 public Optional<Refill> update(UUID id, Refill item) {

  Optional<Refill> old = repo.findById(id);
  if (old.isEmpty()) return Optional.empty();
  item.setId(id);
  return Optional.of(repo.save(item));

 }

 public boolean delete(UUID id) {

  if (!repo.existsById(id)) return false;
  repo.deleteById(id); return true;


 }

}
