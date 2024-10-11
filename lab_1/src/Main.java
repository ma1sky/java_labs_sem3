import java.util.InputMismatchException;
import Controller.controller;
import View.view;
import Model.model;

public class Main {
    public static void main(String[] args) {
        model obj = new model();
        while (true) {
            try {
                obj.setNum(controller.getDouble());

                view.printPrecisions();
                obj.setPrecision(controller.getPrecision());

                view.printStartNumber();
                obj.setStartNumber(controller.getDouble());

                obj.setRoot(model.calcRoot(obj.getNum(), obj.getPrecision(), obj.getStartNumber()));
                view.printRoot(obj.getRoot(), obj.getNum(), obj.getPrecision(), obj.getStartNumber());
            }

            catch (IllegalArgumentException ex) {
                view.printException(ex);
            }
            catch (InputMismatchException ex) {
                System.out.println("Incorrect input. Try again.");
            }
        }
    }
}