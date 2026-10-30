package rw.ac.auca.fuelconsumptiontracker.service;

import java.util.*;
import org.springframework.stereotype.Service;
import rw.ac.auca.fuelconsumptiontracker.domain.LicenseClass;
import rw.ac.auca.fuelconsumptiontracker.repository.LicenseClassRepository;

@Service
public class LicenseClassService {

    private final LicenseClassRepository repo;

    public LicenseClassService(LicenseClassRepository repo) {
        this.repo = repo;
    }

    public LicenseClass save(LicenseClass item) {
        return repo.save(item);
    }

    public List<LicenseClass> all() {
        return repo.findAll();
    }

    public Optional<LicenseClass> byId(UUID id) {
        return repo.findById(id);
    }

    public Optional<LicenseClass> update(UUID id, LicenseClass item) {
        Optional<LicenseClass> old = repo.findById(id);
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