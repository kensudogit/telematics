package com.example.telematics;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VehicleData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int speed;
    private int fuelLevel;
    private int engineTemp;

    // Getters and setters
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public int getEngineTemp() {
        return engineTemp;
    }

    public void setEngineTemp(int engineTemp) {
        this.engineTemp = engineTemp;
    }
}