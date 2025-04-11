package com.example.telematics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String vin;
    private double latitude;
    private double longitude;

    // Getters and setters

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}