package Model;

import java.io.Serial;
import java.io.Serializable;

public class Plane extends BaseDrone implements Serializable {
    private double wingSpan;
    @Serial
    private static final long serialVersionUID = 2L;

    public Plane(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                 double loadCapacity, double maxHeight, String powerType, double size,
                 String takeoffMethod, boolean weatherResistant, double wingSpan) {
        super(maxRange, maxFlightTime, cruiseSpeed, maxSpeed, loadCapacity, maxHeight,
                powerType, size, takeoffMethod, weatherResistant);
        this.wingSpan = wingSpan;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public String toString() {
        return "2 {" +
                "maxRange=" + maxRange +
                ", maxFlightTime=" + maxFlightTime +
                ", cruiseSpeed=" + cruiseSpeed +
                ", maxSpeed=" + maxSpeed +
                ", loadCapacity=" + loadCapacity +
                ", maxHeight=" + maxHeight +
                ", powerType='" + powerType + '\'' +
                ", size=" + size +
                ", takeoffMethod='" + takeoffMethod + '\'' +
                ", weatherResistant=" + weatherResistant +
                ", wingSpan=" + wingSpan +
                '}';
    }

    public boolean changeData(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                              double loadCapacity, double maxHeight, String powerType, double size,
                              String takeoffMethod, boolean weatherResistant, double wingSpan) {
        this.maxRange = maxRange;
        this.maxFlightTime = maxFlightTime;
        this.cruiseSpeed = cruiseSpeed;
        this.maxSpeed = maxSpeed;
        this.loadCapacity = loadCapacity;
        this.maxHeight = maxHeight;
        this.powerType = powerType;
        this.size = size;
        this.takeoffMethod = takeoffMethod;
        this.weatherResistant = weatherResistant;
        this.wingSpan = wingSpan;

        return true;
    }

    public String[][] toTableData() {
        return new String[][]{
                {"Type", "Plane"},
                {"Max Range", String.valueOf(getMaxRange())},
                {"Max Flight Time", String.valueOf(getMaxFlightTime())},
                {"Cruise Speed", String.valueOf(getCruiseSpeed())},
                {"Max Speed", String.valueOf(getMaxSpeed())},
                {"Load Capacity", String.valueOf(getLoadCapacity())},
                {"Max Height", String.valueOf(getMaxHeight())},
                {"Power Type", getPowerType()},
                {"Size", String.valueOf(getSize())},
                {"Takeoff Method", getTakeoffMethod()},
                {"Weather Resistant", String.valueOf(isWeatherResistant())},
                {"Wing Span", String.valueOf(getWingSpan())}
        };
    }

    public Plane(String[] parts) {
        super(Double.parseDouble(parts[0].split("=")[1]),
                Double.parseDouble(parts[1].split("=")[1]),
                Double.parseDouble(parts[2].split("=")[1]),
                Double.parseDouble(parts[3].split("=")[1]),
                Double.parseDouble(parts[4].split("=")[1]),
                Double.parseDouble(parts[5].split("=")[1]),
                parts[6].split("=")[1].replace("'", "").trim(),
                Double.parseDouble(parts[7].split("=")[1]),
                parts[8].split("=")[1].replace("'", "").trim(),
                Boolean.parseBoolean(parts[9].split("=")[1]));
        this.wingSpan = Double.parseDouble(parts[10].split("=")[1].replace("}", "").trim());
    }

    @Override
    public String getPurpose() {
        return "Designed for transporting cargo over long distances";
    }

    public double getWingSpan() {
        return wingSpan;
    }
}