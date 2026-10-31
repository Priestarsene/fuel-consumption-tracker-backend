# Fuel Consumption Tracker
A backend REST API for tracking fuel refills, drivers, vehicles, routes, and trip records. Built with Spring Boot, Spring Data JPA, and PostgreSQL for the Web Technology and Internet module.

NSHUTI MUGABO Arsene

27668



## Project Overview
This application provides a REST API for a fuel consumption tracker with the following domain entities:

LicenseClass — driver license categories (B, C, D, E)

Route — bus routes with route numbers

Driver — drivers with assigned route and license classes (many-to-many)

Vehicle — vehicles with unique plate numbers

Refill — fuel refills with status flow (LOGGED → VERIFIED → APPROVED / REJECTED)

TripRecord — trips recorded only for APPROVED refills

## Tech Stack
Java 21

Spring Boot 4.1.2

Spring Web (REST)

Spring Data JPA (Hibernate)

PostgreSQL 17

Maven (via wrapper)

Postman (API testing)

## Prerequisites
Before running this project, ensure you have:

Java 21 installed (java -version)

PostgreSQL 17 running on localhost:5432

A PostgreSQL database named fuel_consumption_tracker

The DB_PASSWORD environment variable set to your PostgreSQL postgres user password

## PostgreSQL Setup
### Step 1 — Create the database
Open pgAdmin or use psql:

CREATE DATABASE fuel_consumption_tracker;

Or via command line:

psql -U postgres -c "CREATE DATABASE fuel_consumption_tracker;"

### Step 2 — Set the DB_PASSWORD environment variable
The application reads the database password from the DB_PASSWORD environment variable — never hardcoded in the repository.

Windows (PowerShell — permanent for current user):

[Environment]::SetEnvironmentVariable("DB_PASSWORD", "yourPostgresPassword", "User")

Then close and reopen your terminal for the change to take effect.

Verify it's set:

[Environment]::GetEnvironmentVariable("DB_PASSWORD", "User")

## Configuration
Database settings are in src/main/resources/application.properties:

spring.application.name=fuel-consumption-tracker
spring.datasource.url=jdbc:postgresql://localhost:5432/fuel_consumption_tracker
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

The ${DB_PASSWORD} placeholder is resolved from the environment variable at runtime. Hibernate auto-creates tables on first run (ddl-auto=update).

## Running the Application
From the project root, on Windows PowerShell:

.\mvnw.cmd spring-boot:run

On macOS / Linux:

./mvnw spring-boot:run

The application starts on http://localhost:8080.

The first run creates all database tables automatically. On subsequent runs, ddl-auto=update preserves existing data.

API Endpoints
## CRUD (Part A)
All five entities expose the same five routes:

Entity	Base URL
LicenseClass	/api/license-classes
Route	/api/routes
Driver	/api/drivers
Vehicle	/api/vehicles
Refill	/api/refills
For each entity:

POST /save → 201 Created

GET /all → 200 OK

GET /{id} → 200 OK or 404 Not Found

PUT /update/{id} → 200 OK or 404

DELETE /delete/{id} → 200 OK or 404

## Derived queries (Part B)
GET /api/vehicles/by-department?department=logistics — vehicles by department, sorted by plate number

GET /api/refills/by-status?status=LOGGED — refills by status, oldest first

GET /api/refills/between?start=2026-11-01&end=2026-11-30 — refills in a date range

## JPQL queries (Part C)
GET /api/drivers/by-license-class?name=class b — drivers holding a specific license class (case-insensitive)

GET /api/routes/free — routes with no assigned driver

GET /api/license-classes/unused — license classes with no drivers

GET /api/vehicles/of-driver/{driverId} — unique vehicles used by a driver

## Business rules (Part D)
POST /api/refills/save — rejects past dates (400), rejects duplicate driver/date (409), forces status to LOGGED

PATCH /api/refills/{id}/status?status=VERIFIED — enforces status flow (LOGGED → VERIFIED/REJECTED; VERIFIED → APPROVED/REJECTED)

PUT /api/drivers/{driverId}/license-classes/{licenseClassId} — assign license class (409 if already assigned)

PATCH /api/drivers/{driverId}/route?routeNumber=4 — move driver to another route (409 if route is occupied)

## Trip records (Part E)
POST /api/trip-records/save?refillId={id} — save trip (only for APPROVED refills)

GET /api/trip-records/vehicle/{plateNumber} — trip history for a vehicle, newest first

DELETE /api/trip-records/delete/{id} — delete trip record

## Reports and bulk updates (Part F)
GET /api/refills/stats/by-status — count of refills grouped by status

PATCH /api/refills/cancel-day?driverId={id}&date=2026-11-05 — bulk-reject non-APPROVED refills for a driver on a specific date

## Postman Collection
The full API test collection is included at the repository root:

fuel-consumption-tracker-midterm.postman_collection.json

Import it into Postman, set the baseUrl variable to http://localhost:8080, and run the requests in order.

## Project Structure
src/main/java/rw/ac/auca/fuelconsumptiontracker/
├── FuelConsumptionTrackerApplication.java
├── domain/ # JPA entities (LicenseClass, Route, Driver, Vehicle, Refill, TripRecord)
├── repository/ # Spring Data JPA repositories (derived + JPQL queries)
├── service/ # Business logic and validation
└── controller/ # REST endpoints

## License
Academic project — no license.