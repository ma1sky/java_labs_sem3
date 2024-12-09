package Controller;

import Model.QuadCopter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Autotests {

    private static final Path logs = Paths.get("logs.txt");
    private static List<String> logEntries = new ArrayList<>();

    public static void testConstructorWithParameters() {
        try {
            QuadCopter quadCopter = new QuadCopter(1000, 30, 60, 80, 5, 120, "Battery", 1.5, "Vertical", true, "4K");

            assertEqual(1000, quadCopter.getMaxRange(), "Max Range");
            assertEqual(30, quadCopter.getMaxFlightTime(), "Max Flight Time");
            assertEqual(60, quadCopter.getCruiseSpeed(), "Cruise Speed");
            assertEqual(80, quadCopter.getMaxSpeed(), "Max Speed");
            assertEqual(5, quadCopter.getLoadCapacity(), "Load Capacity");
            assertEqual(120, quadCopter.getMaxHeight(), "Max Height");
            assertEqual("Battery", quadCopter.getPowerType(), "Power Type");
            assertEqual(1.5, quadCopter.getSize(), "Size");
            assertEqual("Vertical", quadCopter.getTakeoffMethod(), "Takeoff Method");
            assertEqual(true, quadCopter.isWeatherResistant(), "Weather Resistant");
            assertEqual("4K", quadCopter.getCameraResolution(), "Camera Resolution");
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex, logs);
        }
    }

    public static void testConstructorWithStringArray() {
        try {
            String[] parts = {
                    "maxRange=1000",
                    "maxFlightTime=30",
                    "cruiseSpeed=60",
                    "maxSpeed=80",
                    "loadCapacity=5",
                    "maxHeight=120",
                    "powerType='Battery'",
                    "size=1.5",
                    "takeoffMethod='Vertical'",
                    "weatherResistant=true",
                    "cameraResolution='4K'}"
            };

            QuadCopter quadCopter = new QuadCopter(parts);

            assertEqual(1000, quadCopter.getMaxRange(), "Max Range");
            assertEqual(30, quadCopter.getMaxFlightTime(), "Max Flight Time");
            assertEqual(60, quadCopter.getCruiseSpeed(), "Cruise Speed");
            assertEqual(80, quadCopter.getMaxSpeed(), "Max Speed");
            assertEqual(5, quadCopter.getLoadCapacity(), "Load Capacity");
            assertEqual(120, quadCopter.getMaxHeight(), "Max Height");
            assertEqual("Battery", quadCopter.getPowerType(), "Power Type");
            assertEqual(1.5, quadCopter.getSize(), "Size");
            assertEqual("Vertical", quadCopter.getTakeoffMethod(), "Takeoff Method");
            assertEqual(true, quadCopter.isWeatherResistant(), "Weather Resistant");
            assertEqual("4K", quadCopter.getCameraResolution(), "Camera Resolution");
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex, logs);
        }
    }

    public static void testGetType() {
        try {
            QuadCopter quadCopter = new QuadCopter(1000, 30, 60, 80, 5, 120, "Battery", 1.5, "Vertical", true, "4K");
            assertEqual(1, quadCopter.getType(), "Type");
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex, logs);
        }
    }

    public static void testToString() {
        try {
            QuadCopter quadCopter = new QuadCopter(1000, 30, 60, 80, 5, 120, "Battery", 1.5, "Vertical", true, "4K");
            String expected = "1 {maxRange=1000.0, maxFlightTime=30.0, cruiseSpeed=60.0, maxSpeed=80.0, loadCapacity=5.0, maxHeight=120.0, powerType='Battery', size=1.5, takeoffMethod='Vertical', weatherResistant=true, cameraResolution='4K'}";
            assertEqual(expected, quadCopter.toString(), "ToString");
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex, logs);
        }
    }

    public static void testGetPurpose() {
        try {
            QuadCopter quadCopter = new QuadCopter(1000, 30, 60, 80, 5, 120, "Battery", 1.5, "Vertical", true, "4K");
            assertEqual("Used for photography and surveillance", quadCopter.getPurpose(), "Purpose");
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex, logs);
        }
    }

    public static void assertEqual(Object expected, Object actual, String testName) {
        String result;
        if (expected.equals(actual)) {
            result = testName + " test passed.";
        } else {
            result = testName + " test failed. Expected: " + expected + ", Actual: " + actual;
        }
        logEntries.add(result);
    }

    public static void writeLogToFile() throws IOException {
        Files.write(logs, logEntries);
    }
}