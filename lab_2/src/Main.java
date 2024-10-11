public class Main {
    public static void main(String[] args) {
        Drone[] drones = getDrones();

        for (Drone drone : drones) {
            System.out.println("Drone Type: " + drone.getType());
            System.out.println("Purpose: " + drone.getPurpose());
            System.out.println("Max Range: " + drone.getMaxRange() + " km");
            System.out.println("Max Flight Time: " + drone.getMaxFlightTime() + " minutes");
            System.out.println("Max Speed: " + drone.getMaxSpeed() + " km/h");
            System.out.println("Load Capacity: " + drone.getLoadCapacity() + " kg");
            System.out.println("Max Height: " + drone.getMaxHeight() + " meters");
            System.out.println("Power Type: " + drone.getPowerType());
            System.out.println("Size: " + drone.getSize() + " meters");
            System.out.println("Takeoff Method: " + drone.getTakeoffMethod());
            System.out.println("Weather Resistant: " + (drone.isWeatherResistant() ? "Yes" : "No"));

            if (drone instanceof QuadCopter) {
                System.out.println("Camera Resolution: " + ((QuadCopter) drone).getCameraResolution());
            } else if (drone instanceof Plane) {
                System.out.println("Wing Span: " + ((Plane) drone).getWingSpan() + " meters");
            } else if (drone instanceof Helicopter) {
                System.out.println("Number of Blades: " + ((Helicopter) drone).getNumberOfBlades());
            } else if (drone instanceof HybridDrone) {
                System.out.println("Battery Capacity: " + ((HybridDrone) drone).getBatteryCapacity() + " mAh");
            }

            System.out.println();
        }
    }

    private static Drone[] getDrones() {
        Drone quadCopter = new QuadCopter(20.0, 30.0, 10.0, 20.0, 2.0, 100.0,
                "Electric", 1.5, "Vertical", true, "4K");
        Drone plane = new Plane(100.0, 120.0, 150.0, 250.0, 50.0, 3000.0,
                "Fuel", 10.0, "Horizontal", true, 20.0);
        Drone helicopter = new Helicopter(50.0, 90.0, 120.0, 200.0, 10.0, 2500.0,
                "Fuel", 5.0, "Vertical", true, 4);
        Drone hybridDrone = new HybridDrone(70.0, 60.0, 80.0, 150.0, 15.0, 2000.0,
                "Hybrid (Fuel + Electric)", 3.0, "Vertical", false, 5000.0);

        Drone[] drones = {quadCopter, plane, helicopter, hybridDrone};
        return drones;
    }
}