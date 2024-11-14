class HybridDrone extends BaseDrone {
    private final double batteryCapacity;

    public HybridDrone(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                       double loadCapacity, double maxHeight, String powerType, double size,
                       String takeoffMethod, boolean weatherResistant, double batteryCapacity) {
        super(maxRange, maxFlightTime, cruiseSpeed, maxSpeed, loadCapacity, maxHeight,
                powerType, size, takeoffMethod, weatherResistant);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String getType() {
        return "Hybrid Drone";
    }

    @Override
    public String getPurpose() {
        return "Can be used for both transport and monitoring";
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }
}