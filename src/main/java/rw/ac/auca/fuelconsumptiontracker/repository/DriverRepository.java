package rw.ac.auca.fuelconsumptiontracker.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.ac.auca.fuelconsumptiontracker.domain.Driver;

public interface DriverRepository extends JpaRepository<Driver, UUID> {

    @Query("SELECT DISTINCT d FROM Driver d JOIN d.licenseClasses c WHERE LOWER(c.name) = LOWER(:name)")
    List<Driver> findDriversByLicenseClass(@Param("name") String name);

}