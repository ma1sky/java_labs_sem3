package Model;

import java.io.Serial;

public class HybridDrone extends BaseDrone {
    private final double batteryCapacity;
    @Serial
    private static final long serialVersionUID = 4L;

    public HybridDrone(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                       double loadCapacity, double maxHeight, String powerType, double size,
                       String takeoffMethod, boolean weatherResistant, double batteryCapacity) {
        super(maxRange, maxFlightTime, cruiseSpeed, maxSpeed, loadCapacity, maxHeight,
                powerType, size, takeoffMethod, weatherResistant);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String toString() {
        return "4 {" +
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
                ", batteryCapacity=" + batteryCapacity +
                '}';
    }

    @Override
    public String[][] toTableData() {
        return new String[][]{
                {"Type", "Hybrid Drone"},
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
                {"Battery Capacity", String.valueOf(getBatteryCapacity())}
        };
    }

    public HybridDrone(String[] parts) {
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
        this.batteryCapacity = Double.parseDouble(parts[10].split("=")[1].replace("}", "").trim());
    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public String getPurpose() {
        return "Can be used for both transport and monitoring";
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }
}