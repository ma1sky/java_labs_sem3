public class View {
    public static void printMenu(User user) {
        switch (user.getGroup()) {
            case "user" -> {
                System.out.flush();
                System.out.println("""
                    1. Add drone.
                    2. Remove drone.
                    0. Exit.
                    """
            );}
            case "root" -> {
                System.out.flush();
                System.out.printf(
                    """
                                1. Add drone.
                                2. Remove drone.
                                3. Debugging: %s.
                                4. Autotests: %s.
                                5. Read drone data base from file.
                                6. Write drone data base in file.
                                0. Exit.
                                """, user.isDebugging(), user.isAutotests()
            );}
        }
    }
    public static void printGreeting(String name) {
        System.out.printf("Welcome, %s!%n", name);
    }
}
