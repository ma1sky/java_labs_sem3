package Model;

import java.io.Serial;

public class QuadCopter extends BaseDrone {
    private final String cameraResolution;
    @Serial
    private static final long serialVersionUID = 1L;

    public QuadCopter(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                      double loadCapacity, double maxHeight, String powerType, double size,
                      String takeoffMethod, boolean weatherResistant, String cameraResolution) {
        super(maxRange, maxFlightTime, cruiseSpeed, maxSpeed, loadCapacity, maxHeight,
                powerType, size, takeoffMethod, weatherResistant);
        this.cameraResolution = cameraResolution;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public String toString() {
        return "1 {" +
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
                ", cameraResolution='" + cameraResolution + '\'' +
                '}';
    }

    public String[][] toTableData() {
        return new String[][]{
                {"Type", "QuadCopter"},
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
                {"Camera Resolution", getCameraResolution()}
        };
    }

    public QuadCopter(String[] parts) {
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
        this.cameraResolution = parts[10].split("=")[1].replace("}", "").trim();
    }

    @Override
    public String getPurpose() {
        return "Used for photography and surveillance";
    }

    public String getCameraResolution() {
        return cameraResolution;
    }
}