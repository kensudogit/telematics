package com.example.telematics.service;

import com.example.telematics.entity.Vehicle;
import com.example.telematics.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public void updateLocation(Long vehicleId, double latitude, double longitude) {
        // Logic to update vehicle location in the database
        // This could involve finding the vehicle by ID and updating its location fields
    }

    public Optional<Vehicle> findNearestVehicle(double latitude, double longitude) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .min(Comparator.comparingDouble(
                        vehicle -> distance(vehicle.getLatitude(), vehicle.getLongitude(), latitude, longitude)));
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        // Haversine formula to calculate the distance between two points on the Earth
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }

    public String generateSafetyReport(Long vehicleId) {
        // Placeholder logic for generating a safety report
        // This could involve analyzing speed, braking patterns, etc.
        return "Safety report for vehicle " + vehicleId + ": All systems normal.";
    }

    public String generateDailyReport(Long vehicleId) {
        // Placeholder logic for generating a daily report
        // This could involve summarizing the vehicle's usage for the day
        return "Daily report for vehicle " + vehicleId + ": No issues reported.";
    }
}