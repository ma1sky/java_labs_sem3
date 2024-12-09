package Model;

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
}
