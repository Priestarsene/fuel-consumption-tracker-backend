package rw.ac.auca.fuelconsumptiontracker.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.fuelconsumptiontracker.domain.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    List<Vehicle> findByDepartmentIgnoreCaseOrderByPlateNumberAsc(String department);
    
    @Query("SELECT DISTINCT r.vehicle FROM Refill r WHERE r.driver.id = :driverId")
    List<Vehicle> findVehiclesOfDriver(@Param("driverId") UUID driverId);

}