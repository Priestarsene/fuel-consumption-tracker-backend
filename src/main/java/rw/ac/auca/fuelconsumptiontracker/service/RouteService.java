package rw.ac.auca.fuelconsumptiontracker.service;

import java.util.*;
import org.springframework.stereotype.Service;
import rw.ac.auca.fuelconsumptiontracker.domain.Route;
import rw.ac.auca.fuelconsumptiontracker.repository.RouteRepository;

@Service
public class RouteService {

    private final RouteRepository repo;

    public RouteService(RouteRepository repo) {
        this.repo = repo;
    }

    public Route save(Route item) {
        return repo.save(item);
    }

    public List<Route> all() {
        return repo.findAll();
    }

    public Optional<Route> byId(UUID id) {
        return repo.findById(id);
    }

    public Optional<Route> update(UUID id, Route item) {
        Optional<Route> old = repo.findById(id);
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