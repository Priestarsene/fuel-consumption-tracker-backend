package rw.ac.auca.fuelconsumptiontracker.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.fuelconsumptiontracker.domain.Refill;
import rw.ac.auca.fuelconsumptiontracker.domain.RefillStatus;
import java.time.LocalDate;

public interface RefillRepository extends JpaRepository<Refill, UUID> {

    List<Refill> findByStatusOrderByRefillDateAsc(RefillStatus status);
    List<Refill> findByRefillDateBetweenOrderByRefillDateAsc(LocalDate start, LocalDate end);

}