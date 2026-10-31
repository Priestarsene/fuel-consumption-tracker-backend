package rw.ac.auca.fuelconsumptiontracker.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.ac.auca.fuelconsumptiontracker.domain.Route;

public interface RouteRepository extends JpaRepository<Route, UUID> {

    @Query("SELECT r FROM Route r WHERE NOT EXISTS (SELECT d FROM Driver d WHERE d.route = r) ORDER BY r.routeNumber ASC")
    List<Route> findFreeRoutes();

}