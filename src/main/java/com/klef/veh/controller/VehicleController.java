package com.klef.veh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.veh.entity.Vehicle;
import com.klef.veh.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	@Autowired
private VehicleService service;
 @PostMapping("/add")
	public ResponseEntity<Vehicle> insert(@RequestBody Vehicle v) {
		Vehicle vehicle=service.insertvehicle(v); 
		return ResponseEntity.ok(vehicle);
	}
 @DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delect(@PathVariable long id) {
		String vehicle=service.deletevehicle(id);
		return ResponseEntity.ok(vehicle);
	}
 @PutMapping("/update")
 public ResponseEntity<?> update(@RequestBody Vehicle v) {

     Vehicle vehicle = service.updatevehicle(v);

     if (vehicle != null) {
         return ResponseEntity.ok(vehicle);
     } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
     }
 }
 @GetMapping("/displayall")
 public ResponseEntity<List<Vehicle>> displayall() {
	 List<Vehicle> vehicle=service.displayallvehicle();
	 return ResponseEntity.ok(vehicle);
 }
 @GetMapping("/displaybyid/{id}")
	public ResponseEntity<?> displaybyid(@PathVariable long id) {
	 Vehicle vehicle=service.displaybyid(id);

     if (vehicle != null) {
         return ResponseEntity.ok(vehicle);
     } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
     }
 }
 @GetMapping("/findbycompany")
 public ResponseEntity<List<Vehicle>> findbycompany(@PathVariable String company) {
	 List<Vehicle> v=service.findByCompany(company);
	 return ResponseEntity.ok(v);
 }
 @GetMapping("/findbyfueltype")
 public ResponseEntity<List<Vehicle>> finfbyfuel(@PathVariable String fuelType) {
	 List<Vehicle> v=service.findByFuelType(fuelType);
	 return ResponseEntity.ok(v);
 }
}
