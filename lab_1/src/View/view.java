package View;

public class view {
    public static void printPrecisions() {
        System.out.println("Choose precision:");
        System.out.println("1: 1%");
        System.out.println("2: 0.1%");
        System.out.println("3: 0.01");
        System.out.println("4: 0.001");
        System.out.println("5: 0.0001");
    }
    public static void printGetNum() {
        System.out.print("Write real number(0 = exit): ");
    }

    public static void printGetInt() {
        System.out.print("Write int number(0 = exit): ");
    }

    public static void printStartNumber() {
        System.out.print("Write start number. ");
    }

    public static void printException(Exception ex) {
        System.out.print("Exception: ");
        System.out.println(ex.getMessage());
    }

    public static void printRoot(double root, double num,double precision, double start) {
        System.out.printf("Calculated root = %.4f;\n" +
                "Of number = %.4f;\n" +
                "With precision = %.4f;\n" +
                "And start num = %.4f.\n"
                , root, num, precision, start);
    }
}
