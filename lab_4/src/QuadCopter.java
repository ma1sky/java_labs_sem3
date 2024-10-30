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