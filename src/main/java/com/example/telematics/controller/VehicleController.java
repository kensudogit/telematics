package com.example.telematics.controller;

import com.example.telematics.entity.Vehicle;
import com.example.telematics.service.VehicleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/gps")
    public void updateVehicleLocation(@RequestParam Long vehicleId, @RequestParam double latitude,
            @RequestParam double longitude) {
        vehicleService.updateLocation(vehicleId, latitude, longitude);
    }

    @GetMapping("/nearest")
    public Vehicle getNearestVehicle(@RequestParam double latitude, @RequestParam double longitude) {
        return vehicleService.findNearestVehicle(latitude, longitude)
                .orElseThrow(() -> new RuntimeException("No vehicles available"));
    }

    @GetMapping("/safety-report")
    public String getSafetyReport(@RequestParam Long vehicleId) {
        return vehicleService.generateSafetyReport(vehicleId);
    }

    @GetMapping("/daily-report")
    public String getDailyReport(@RequestParam Long vehicleId) {
        return vehicleService.generateDailyReport(vehicleId);
    }
}