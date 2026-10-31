package rw.ac.auca.fuelconsumptiontracker.service;

import java.util.*;
import org.springframework.stereotype.Service;
import rw.ac.auca.fuelconsumptiontracker.domain.Driver;
import rw.ac.auca.fuelconsumptiontracker.repository.DriverRepository;
import rw.ac.auca.fuelconsumptiontracker.repository.LicenseClassRepository;
import rw.ac.auca.fuelconsumptiontracker.domain.LicenseClass;
import rw.ac.auca.fuelconsumptiontracker.repository.RouteRepository;
import rw.ac.auca.fuelconsumptiontracker.domain.Route;


@Service
public class DriverService {

 private final DriverRepository repo;
 private final LicenseClassRepository licenseClassRepository;
 private final RouteRepository routeRepository;

 public DriverService(DriverRepository repo,
                     LicenseClassRepository licenseClassRepository,
                     RouteRepository routeRepository) {
    this.repo = repo;
    this.licenseClassRepository = licenseClassRepository;
    this.routeRepository = routeRepository;
 
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

public List<Driver> byLicenseClass(String name) {

    return repo.findDriversByLicenseClass(name);

}

public String addLicenseClass(UUID driverId, UUID classId) {
    
    Optional<Driver> driver = repo.findById(driverId);
    if (driver.isEmpty()) return "The driver with that id does not exist";
    Optional<LicenseClass> licenseClass = licenseClassRepository.findById(classId);
    if (licenseClass.isEmpty()) return "That license class does not exist";
    if (driver.get().getLicenseClasses().contains(licenseClass.get()))
        return "The driver already has that license class";
    driver.get().getLicenseClasses().add(licenseClass.get());
    repo.save(driver.get());
    return "License class added to the driver";

}

public String changeRoute(UUID driverId, int number) {
    Optional<Driver> driver = repo.findById(driverId);
    if (driver.isEmpty()) return "The driver with that id does not exist";
    Optional<Route> route = routeRepository.findByRouteNumber(number);
    if (route.isEmpty()) return "The route with that routeNumber does not exist";
    if (repo.existsByRoute(route.get()) && !route.get().equals(driver.get().getRoute()))
        return "That route is taken by another driver";
    driver.get().setRoute(route.get());
    repo.save(driver.get());
    return "Route changed successfully";
}

}
