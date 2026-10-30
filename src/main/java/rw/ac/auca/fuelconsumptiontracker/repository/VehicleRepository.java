package rw.ac.auca.fuelconsumptiontracker.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.fuelconsumptiontracker.domain.Vehicle;


public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {


 }
