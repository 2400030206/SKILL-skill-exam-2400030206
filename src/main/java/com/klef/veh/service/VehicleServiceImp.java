package com.klef.veh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.veh.entity.Vehicle;
import com.klef.veh.repository.VehicleRepository;

@Service
public class VehicleServiceImp implements VehicleService {

    @Autowired
    private VehicleRepository repo;

    @Override
    public Vehicle insertvehicle(Vehicle v) {
        return repo.save(v);
    }

    @Override
    public Vehicle updatevehicle(Vehicle v) {

        Optional<Vehicle> optional = repo.findById(v.getVid());

        if (optional.isPresent()) {

            Vehicle vehicle = optional.get();

            vehicle.setModel(v.getModel());
            vehicle.setCompany(v.getCompany());   
            vehicle.setFuelType(v.getFuelType());
            vehicle.setColor(v.getColor());
            vehicle.setPrice(v.getPrice());

            return repo.save(vehicle);
        }

        return null;
    }

    @Override
    public String deletevehicle(long id) {
boolean flag=repo.existsById(id);
        if (flag) {

            repo.deleteById(id);

            return "Vehicle deleted successfully";
        }

        return "Vehicle not found";
    }

    @Override
    public List<Vehicle> displayallvehicle() {
        return repo.findAll();
    }

    @Override
    public Vehicle displaybyid(long id) {
        return repo.findById(id).orElse(null);
    }

	@Override
	public List<Vehicle> findByCompany(String company) {
		return repo.findByCompany(company);
	}

	@Override
	public List<Vehicle> findByFuelType(String fuelType) {
		return repo.findByFuelType(fuelType);
	}

	
	
}