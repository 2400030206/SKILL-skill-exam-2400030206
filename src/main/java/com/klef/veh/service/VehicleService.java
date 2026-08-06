package com.klef.veh.service;

import java.util.List;

import com.klef.veh.entity.Vehicle;

public interface VehicleService  {
Vehicle insertvehicle(Vehicle v);
Vehicle updatevehicle(Vehicle v);
String deletevehicle(long id);
List<Vehicle> displayallvehicle();
Vehicle displaybyid(long id);
List<Vehicle> findByCompany(String company);
List<Vehicle> findByFuelType(String fuelType);
}
