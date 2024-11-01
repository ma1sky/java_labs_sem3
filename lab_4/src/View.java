import java.util.ArrayList;

public class View {
    public static void printMenu(User user) {
        switch (user.getGroup()) {
            case "user" -> {
                System.out.printf("""
                    Debugging = %s;
                    1. Read drone data base from file.
                    2. Write drone data base in file.
                    3. Add drone.
                    4. Remove drone by ID.
                    5. Print data.
                    6. Change drone.
                    0. Exit.
                    """
                        , user.isDebugging());}
            case "root" -> {
                System.out.printf(
                        """
                                    1. Read drone data base from file.
                                    2. Write drone data base in file.
                                    3. Add drone.
                                    4. Remove drone by ID.
                                    5. Print data.
                                    6. Change drone.
                                    7. Debugging: %s.
                                    8. Autotests: %s.
                                    0. Exit.
                                    """, user.isDebugging(), user.isAutotests()
                );}
        }
    }
    public static void printGreeting(String name) {
        System.out.printf("Welcome, %s!%n", name);
    }

    public static void printDrones(ArrayList<BaseDrone> drones) {
        int i = 0;
        for (BaseDrone drone : drones) {
            System.out.println("ID: " + i++ + " " + drone.toString());
        }
    }
}