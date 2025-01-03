import java.io.Serializable;

interface Drone extends Serializable {
    int getType();
    String getPurpose();
    double getMaxRange();
    double getMaxFlightTime();
    double getCruiseSpeed();
    double getMaxSpeed();
    double getLoadCapacity();
    double getMaxHeight();
    String getPowerType();
    double getSize();
    String getTakeoffMethod();
    boolean isWeatherResistant();
    String toString();

    default double calculateReducedFlightTime() {
        double maxFlightTime = getMaxFlightTime();
        double cruiseSpeed = getCruiseSpeed();
        double maxSpeed = getMaxSpeed();
        double coefficient = 0.3;

        return maxFlightTime * (1 - coefficient * (maxSpeed - cruiseSpeed) / maxSpeed);
    }
}