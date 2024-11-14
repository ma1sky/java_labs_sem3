class QuadCopter extends BaseDrone {
    private final String cameraResolution;

    public QuadCopter(double maxRange, double maxFlightTime, double cruiseSpeed, double maxSpeed,
                      double loadCapacity, double maxHeight, String powerType, double size,
                      String takeoffMethod, boolean weatherResistant, String cameraResolution) {
        super(maxRange, maxFlightTime, cruiseSpeed, maxSpeed, loadCapacity, maxHeight,
                powerType, size, takeoffMethod, weatherResistant);
        this.cameraResolution = cameraResolution;
    }

    @Override
    public String getType() {
        return "QuadCopter";
    }

    @Override
    public String getPurpose() {
        return "Used for photography and surveillance";
    }

    public String getCameraResolution() {
        return cameraResolution;
    }
}