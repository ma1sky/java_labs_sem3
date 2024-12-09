package Controller;

import Model.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Controller {
    public static Path logs = Path.of("logs.txt");

    public static String scanString(String desc) {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.print(desc + " ");
        while (true) {
            try{
                str = sc.nextLine();
                if (str.isEmpty()) {
                    throw new IllegalArgumentException("\nLine is empty. Write something please.");
                }
                if (str.replaceAll("(?U)[\\pP\\s]", "").trim().isEmpty()) {
                    throw new IllegalArgumentException("\nOnly symbols of punctuation.");
                } else { break; }
            } catch (IllegalArgumentException e) {
                ExceptionHandler.handleException(e, logs);
            }

        }
        return str;
    }

    public static double scanDouble(String desc) {
        System.out.println(desc + ": ");
        Scanner sn = new Scanner(System.in);
        String input = new String();
        double num = -1;
        while(true) {
            try {
                input = sn.nextLine();
                num = Double.parseDouble(input);
                if (num < 0) {throw new IllegalArgumentException("Number must be positive number");}
                return num;
            }
            catch (InputMismatchException | NumberFormatException ex) {
                ExceptionHandler.handleException(ex, logs);
            }
        }
    }

    public static int scanInt(String desc, int hlim, int llim) {
        System.out.println(desc + ": ");
        Scanner sn = new Scanner(System.in);
        String input = new String();
        int num = -1;
        while(true) {
            try {
                input = sn.nextLine();
                num = Integer.parseInt(input);
                if (llim > num || num > hlim) {throw new IllegalArgumentException("Number must be >" + llim + "and <" + hlim);}
                break;
            }
            catch (InputMismatchException | NumberFormatException ex) {
                ExceptionHandler.handleException(ex, logs);
            }
        }
        return num;
    }

    public static boolean scanBoolean(String desc) {
        System.out.println(desc + ": ");
        Scanner sn = new Scanner(System.in);
        String input = new String();
        boolean num;
        while(true) {
            try {
                input = sn.nextLine();
                num = Boolean.parseBoolean(input);
                return num;
            }
            catch (InputMismatchException | NumberFormatException ex) {
                ExceptionHandler.handleException(ex, logs);
            }
        }
    }

    public static void autotestsStart() throws IOException {
        Autotests.testConstructorWithParameters();
        Autotests.testConstructorWithStringArray();
        Autotests.testGetType();
        Autotests.testToString();
        Autotests.testGetPurpose();
        Autotests.writeLogToFile();
    }

}