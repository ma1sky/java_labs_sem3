package Controller;
import java.util.InputMismatchException;
import java.util.Scanner;
import View.view;

public class controller {
    public static double getDouble() {
        Scanner sn = new Scanner(System.in);
        String input;
        view.printGetNum();
        input = sn.nextLine();
        if (input.isEmpty()) { throw new IllegalArgumentException("Line is empty");}
        double num = Double.parseDouble(input);
        if (num < 0) { throw new IllegalArgumentException("Number must be real and positive");}
        if (num == 0) { System.exit(0); }
        return num;
    }

    public static int getInt() {
        Scanner sn = new Scanner(System.in);
        String input = new String();
        try {
            view.printGetInt();
            input = sn.nextLine();
        }
        catch (InputMismatchException | NumberFormatException ex) {
            view.printException(ex);
        }
        return Integer.parseInt(input);
    }

    public static double getPrecision() {
        while (true) {
            try {
                int menu = controller.getInt();
                switch (menu) {
                    case 1: return 1;
                    case 2: return 0.1;
                    case 3: return 0.01;
                    case 4: return 0.001;
                    case 5: return 0.0001;
                    case 0: System.exit(0);
                    default: throw new NumberFormatException("Number must be int and <=5 and >=0");
                }
            } catch (NumberFormatException ex) {
                view.printException(ex);
            }
        }
    }


}
