package rw.ac.auca.fuelconsumptiontracker.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.ac.auca.fuelconsumptiontracker.domain.LicenseClass;

public interface LicenseClassRepository extends JpaRepository<LicenseClass, UUID> {

    @Query("SELECT c FROM LicenseClass c WHERE c.drivers IS EMPTY")
    List<LicenseClass> findUnused();


}