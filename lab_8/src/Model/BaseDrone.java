package Model;

import java.util.Random;

public abstract class BaseDrone implements Drone {
    protected double maxRange;
    protected double maxFlightTime;
    protected double cruiseSpeed;
    protected double maxSpeed;
    protected double loadCapacity;
    protected double maxHeight;
    protected String powerType;
    protected double size;
    protected String takeoffMethod;
    protected boolean weatherResistant;

    public BaseDrone(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                     double loadCapacity, double maxHeight, String powerType, double size,
                     String takeoffMethod, boolean weatherResistant) {
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
    }

    public BaseDrone() {
        this.maxRange = 0;
        this.maxFlightTime = 0;
        this.cruiseSpeed = 0;
        this.maxSpeed = 0;
        this.loadCapacity = 0;
        this.maxHeight = 0;
        this.powerType = null;
        this.size = 0;
        this.takeoffMethod = null;
        this.weatherResistant = false;
    }

    @Override
    public abstract  String[][] toTableData();

    @Override
    public double getMaxRange() {
        return maxRange;
    }

    @Override
    public double getMaxFlightTime() {
        return maxFlightTime;
    }

    @Override
    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public double getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public double getMaxHeight() {
        return maxHeight;
    }

    @Override
    public String getPowerType() {
        return powerType;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public String getTakeoffMethod() {
        return takeoffMethod;
    }

    @Override
    public boolean isWeatherResistant() {
        return weatherResistant;
    }

    @Override
    public abstract int getType();

    @Override
    public abstract String toString();

    public static BaseDrone createRandomDrone() {
        Random random = new Random();
        return switch (random.nextInt(4)) {
            case 0 ->
                    new QuadCopter(
                            random.nextDouble() * 50 + 10, // range
                            random.nextDouble() * 60 + 10, // flight time
                            random.nextDouble() * 20 + 5,  // speed
                            random.nextDouble() * 30 + 10, // load capacity
                            random.nextDouble() * 5 + 1,   // height
                            random.nextDouble() * 500 + 100, // size
                            "Electric",                    // power type
                            random.nextDouble() * 2 + 0.5, // size
                            "Vertical",                    // takeoff method
                            random.nextBoolean(),          // weather resistant
                            "4K"                           // camera resolution
                    );
            case 1 ->
                    new Plane(
                            random.nextDouble() * 200 + 50, // range
                            random.nextDouble() * 180 + 60, // flight time
                            random.nextDouble() * 300 + 100, // speed
                            random.nextDouble() * 500 + 100, // load capacity
                            random.nextDouble() * 50 + 10,  // height
                            random.nextDouble() * 5000 + 1000, // size
                            "Fuel",                         // power type
                            random.nextDouble() * 15 + 5,  // wing span
                            "Horizontal",                  // takeoff method
                            random.nextBoolean(),          // weather resistant
                            random.nextDouble() * 30 + 5   // wing span
                    );
            case 2 ->
                    new Helicopter(
                            random.nextDouble() * 150 + 30, // range
                            random.nextDouble() * 120 + 40, // flight time
                            random.nextDouble() * 200 + 50, // speed
                            random.nextDouble() * 300 + 50, // load capacity
                            random.nextDouble() * 25 + 5,   // height
                            random.nextDouble() * 4000 + 500, // size
                            "Fuel",                         // power type
                            random.nextDouble() * 5 + 1,   // rotor diameter
                            "Vertical",                    // takeoff method
                            random.nextBoolean(),          // weather resistant
                            random.nextInt(6) + 2          // number of blades
                    );
            case 3 ->
                    new HybridDrone(
                            random.nextDouble() * 100 + 40, // range
                            random.nextDouble() * 90 + 30,  // flight time
                            random.nextDouble() * 250 + 50, // speed
                            random.nextDouble() * 400 + 50, // load capacity
                            random.nextDouble() * 40 + 10,  // height
                            random.nextDouble() * 6000 + 1500, // size
                            "Hybrid (Fuel + Electric)",    // power type
                            random.nextDouble() * 3 + 1,   // battery capacity
                            "Vertical",                    // takeoff method
                            random.nextBoolean(),          // weather resistant
                            random.nextDouble() * 6000 + 1000 // battery capacity
                    );
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(4));
        };
    }
}
