package Model;

import java.io.Serial;

public class Helicopter extends BaseDrone {
    private int numberOfBlades;
    @Serial
    private static final long serialVersionUID = 3L;

    public Helicopter(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                      double loadCapacity, double maxHeight, String powerType, double size,
                      String takeoffMethod, boolean weatherResistant, int numberOfBlades) {
        super(maxRange, maxFlightTime, cruiseSpeed, maxSpeed, loadCapacity, maxHeight,
                powerType, size, takeoffMethod, weatherResistant);
        this.numberOfBlades = numberOfBlades;
    }

    public Helicopter() {
        super();
        this.numberOfBlades = 0;
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public String toString() {
        return "3 {" +
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
                ", numberOfBlades=" + numberOfBlades +
                '}';
    }

    public boolean changeData(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                              double loadCapacity, double maxHeight, String powerType, double size,
                              String takeoffMethod, boolean weatherResistant, int numberOfBlades) {
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
        this.numberOfBlades = numberOfBlades;

        return true;
    }

    public String[][] toTableData() {
        return new String[][]{
                {"Type", "Helicopter"},
                {"Max Range", String.valueOf(maxRange)},
                {"Max Flight Time", String.valueOf(maxFlightTime)},
                {"Cruise Speed", String.valueOf(cruiseSpeed)},
                {"Max Speed", String.valueOf(maxSpeed)},
                {"Load Capacity", String.valueOf(loadCapacity)},
                {"Max Height", String.valueOf(maxHeight)},
                {"Power Type", powerType},
                {"Size", String.valueOf(size)},
                {"Takeoff Method", takeoffMethod},
                {"Weather Resistant", String.valueOf(weatherResistant)},
                {"Number of Blades", String.valueOf(numberOfBlades)}
        };
    }

    public Helicopter(String[] parts) {
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
        this.numberOfBlades = Integer.parseInt(parts[10].split("=")[1].replace("}", "").trim());
    }

    @Override
    public String getPurpose() {
        return "Used for quick transport and rescue operations";
    }

    public int getNumberOfBlades() {
        return numberOfBlades;
    }
}