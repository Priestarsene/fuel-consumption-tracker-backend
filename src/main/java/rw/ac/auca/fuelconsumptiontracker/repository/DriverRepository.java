package rw.ac.auca.fuelconsumptiontracker.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.fuelconsumptiontracker.domain.Driver;

public interface DriverRepository extends JpaRepository<Driver, UUID> {

    
 }
