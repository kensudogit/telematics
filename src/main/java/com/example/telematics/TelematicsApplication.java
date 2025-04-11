package com.example.telematics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.telematics.service.TelematicsService; // Adjust the package path as necessary

@SpringBootApplication
public class TelematicsApplication {

	private static final Logger logger = Logger.getLogger(TelematicsApplication.class.getName());

	@Autowired
	private TelematicsService telematicsService;

	public static void main(String[] args) {
		SpringApplication.run(TelematicsApplication.class, args);
		TelematicsApplication app = new TelematicsApplication();
		app.runTelematicsSystem();
	}

	private void runTelematicsSystem() {
		logger.info("Welcome to the Telematics System");

		// データ収集
		VehicleData data = collectVehicleData();

		// データ通信
		sendDataToServer(data);

		// データ処理
		processData(data);

		// 情報サービス提供
		provideInformationService(data);

		// データ保存
		telematicsService.saveVehicleData(data);
	}

	private VehicleData collectVehicleData() {
		// 車両データを収集するロジック
		VehicleData data = new VehicleData();
		data.setSpeed(80); // km/h
		data.setFuelLevel(50); // percentage
		data.setEngineTemp(90); // Celsius
		data.setGpsCoordinates("35.6895° N, 139.6917° E"); // Example GPS coordinates
		return data;
	}

	private static void sendDataToServer(VehicleData data) {
		// データをサーバーに送信するロジック
		try {
			URL url = new URL("http://example.com/api/vehicledata");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			String jsonInputString = String.format(
					"{\"speed\": %f, \"fuelLevel\": %f, \"engineTemp\": %f, \"gpsCoordinates\": \"%s\"}",
					data.getSpeed(), data.getFuelLevel(), data.getEngineTemp(), data.getGpsCoordinates());

			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			int responseCode = conn.getResponseCode();
			logger.info("POST Response Code :: " + responseCode);

		} catch (Exception e) {
			logger.severe("Error sending data to server: " + e.getMessage());
		}
	}

	private static void processData(VehicleData data) {
		// データを処理するロジック
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

	private static void provideInformationService(VehicleData data) {
		// 情報サービスを提供するロジック
		logger.info("Current Speed: " + data.getSpeed() + " km/h");
		logger.info("Fuel Level: " + data.getFuelLevel() + "%");
		logger.info("Engine Temperature: " + data.getEngineTemp() + "°C");
		logger.info("GPS Coordinates: " + data.getGpsCoordinates());
	}
}

@Entity
class VehicleData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double speed;
	private double fuelLevel;
	private double engineTemp;
	private String gpsCoordinates;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(double fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

	public double getEngineTemp() {
		return engineTemp;
	}

	public void setEngineTemp(double engineTemp) {
		this.engineTemp = engineTemp;
	}

	public String getGpsCoordinates() {
		return gpsCoordinates;
	}

	public void setGpsCoordinates(String gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}
}
