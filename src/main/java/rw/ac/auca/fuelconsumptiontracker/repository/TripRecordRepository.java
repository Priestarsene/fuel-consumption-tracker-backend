package rw.ac.auca.fuelconsumptiontracker.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.ac.auca.fuelconsumptiontracker.domain.TripRecord;

public interface TripRecordRepository extends JpaRepository<TripRecord, UUID> {

    @Query("SELECT t FROM TripRecord t WHERE t.refill.vehicle.plateNumber = :plateNumber ORDER BY t.tripDate DESC")
    List<TripRecord> findByVehiclePlate(@Param("plateNumber") String plateNumber);
    boolean existsByRefillId(UUID refillId);

}