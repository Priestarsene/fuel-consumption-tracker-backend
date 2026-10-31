package rw.ac.auca.fuelconsumptiontracker.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.fuelconsumptiontracker.domain.Refill;
import rw.ac.auca.fuelconsumptiontracker.domain.RefillStatus;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;


public interface RefillRepository extends JpaRepository<Refill, UUID> {

    List<Refill> findByStatusOrderByRefillDateAsc(RefillStatus status);
    List<Refill> findByRefillDateBetweenOrderByRefillDateAsc(LocalDate start, LocalDate end);
    boolean existsByDriverIdAndRefillDateAndStatusNot(UUID driverId, LocalDate refillDate, RefillStatus status);
    
    @Query("SELECT r.status, COUNT(r) FROM Refill r GROUP BY r.status")
    List<Object[]> countRefillsByStatus();

    @Modifying
    @Query("UPDATE Refill r SET r.status = :rejected WHERE r.driver.id = :driverId AND r.refillDate = :date AND r.status <> :approved")
    int rejectDriverDay(@Param("driverId") UUID driverId,
                    @Param("date") LocalDate date,
                    @Param("rejected") RefillStatus rejected,
                    @Param("approved") RefillStatus approved);

}