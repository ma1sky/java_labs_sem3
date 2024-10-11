class Plane extends BaseDrone {
    private final double wingSpan;

    public Plane(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                 double loadCapacity, double maxHeight, String powerType, double size,
                 String takeoffMethod, boolean weatherResistant, double wingSpan) {
        super(maxRange, maxFlightTime, cruiseSpeed, maxSpeed, loadCapacity, maxHeight,
                powerType, size, takeoffMethod, weatherResistant);
        this.wingSpan = wingSpan;
    }

    @Override
    public String getType() {
        return "Plane";
    }

    @Override
    public String getPurpose() {
        return "Designed for transporting cargo over long distances";
    }

    public double getWingSpan() {
        return wingSpan;
    }
}