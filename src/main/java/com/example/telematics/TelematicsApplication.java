package com.example.telematics;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;

@SpringBootApplication
public class TelematicsApplication {

	private static final Logger logger = Logger.getLogger(TelematicsApplication.class.getName());

	public static void main(String[] args) {
		logger.info("Welcome to the Telematics System");
		VehicleData data = collectData();
		processData(data);
	}

	public static VehicleData collectData() {
		// Simulate data collection from a vehicle
		VehicleData data = new VehicleData();
		data.setSpeed(80); // km/h
		data.setFuelLevel(50); // percentage
		data.setEngineTemp(90); // Celsius
		return data;
	}

	public static void processData(VehicleData data) {
		// Process the collected data
		if (data.getSpeed() > 100) {
			logger.warning("Warning: Speed is too high!");
		}
		if (data.getFuelLevel() < 20) {
			logger.warning("Warning: Fuel level is low!");
		}
		if (data.getEngineTemp() > 100) {
			logger.warning("Warning: Engine temperature is too high!");
		}
	}
}
