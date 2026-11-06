package com.klef.veh.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.veh.entity.Vehicle;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    private Vehicle toyotaCorolla;
    private Vehicle teslaModel3;
    private Vehicle hondaCivic;

    @BeforeEach
    void setUp() {
        vehicleRepository.deleteAll();

        toyotaCorolla = createVehicle("Corolla", "Toyota", "Petrol", "White", 25000.0);
        teslaModel3 = createVehicle("Model 3", "Tesla", "Electric", "Red", 42000.0);
        hondaCivic = createVehicle("Civic", "Honda", "Petrol", "Blue", 28000.0);

        vehicleRepository.saveAndFlush(toyotaCorolla);
        vehicleRepository.saveAndFlush(teslaModel3);
        vehicleRepository.saveAndFlush(hondaCivic);
    }

    @Test
    void findAll_returnsAllVehicles() {
        var vehicles = vehicleRepository.findAll();

        assertNotNull(vehicles);
        assertEquals(3, vehicles.size());
        assertTrue(vehicles.stream().anyMatch(vehicle -> "Corolla".equals(vehicle.getModel())));
        assertTrue(vehicles.stream().anyMatch(vehicle -> "Model 3".equals(vehicle.getModel())));
        assertTrue(vehicles.stream().anyMatch(vehicle -> "Civic".equals(vehicle.getModel())));
    }

    @Test
    void findById_returnsVehicleWhenPresentAndEmptyWhenMissing() {
        var found = vehicleRepository.findById(toyotaCorolla.getVid());

        assertTrue(found.isPresent());
        assertEquals(toyotaCorolla.getVid(), found.get().getVid());

        assertFalse(vehicleRepository.findById(Long.MAX_VALUE).isPresent());
    }

    @Test
    void findByDerivedQueries_returnsVehiclesMatchingFuelTypeAndCompany() {
        var petrolVehicles = vehicleRepository.findByFuelType("Petrol");
        var teslaVehicles = vehicleRepository.findByCompany("Tesla");

        assertEquals(2, petrolVehicles.size());
        assertTrue(petrolVehicles.stream().allMatch(vehicle -> "Petrol".equals(vehicle.getFuelType())));
        assertEquals(1, teslaVehicles.size());
        assertEquals("Tesla", teslaVehicles.get(0).getCompany());
    }

    @Test
    void count_returnsNumberOfPreparedVehicles() {
        assertEquals(3, vehicleRepository.count());
    }

    @Test
    void findById_returnsVehicleWithRequiredProperties() {
        var found = vehicleRepository.findById(teslaModel3.getVid());

        assertTrue(found.isPresent());
        Vehicle vehicle = found.get();
        assertNotNull(vehicle.getModel());
        assertNotNull(vehicle.getCompany());
        assertNotNull(vehicle.getFuelType());
        assertNotNull(vehicle.getColor());
        assertEquals(42000.0, vehicle.getPrice());
    }

    private Vehicle createVehicle(String model, String company, String fuelType, String color, double price) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(model);
        vehicle.setCompany(company);
        vehicle.setFuelType(fuelType);
        vehicle.setColor(color);
        vehicle.setPrice(price);
        return vehicle;
    }
}