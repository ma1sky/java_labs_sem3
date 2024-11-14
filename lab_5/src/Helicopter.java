class Helicopter extends BaseDrone {
    private final int numberOfBlades;

    public Helicopter(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                      double loadCapacity, double maxHeight, String powerType, double size,
                      String takeoffMethod, boolean weatherResistant, int numberOfBlades) {
        super(maxRange, maxFlightTime, cruiseSpeed, maxSpeed, loadCapacity, maxHeight,
                powerType, size, takeoffMethod, weatherResistant);
        this.numberOfBlades = numberOfBlades;
    }

    @Override
    public String getType() {
        return "Helicopter";
    }

    @Override
    public String getPurpose() {
        return "Used for quick transport and rescue operations";
    }

    public int getNumberOfBlades() {
        return numberOfBlades;
    }
}