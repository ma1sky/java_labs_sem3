public class View {
    public static void printMenu(User user) {
        switch (user.getGroup()) {
            case "user" -> {
                System.out.flush();
                System.out.printf("""
                    Debugging = %s;
                    1. Read drone data base from file.
                    2. Write drone data base in file.
                    3. Add random drone.
                    4. Remove random drone.
                    0. Exit.
                    """
            , user.isDebugging());}
            case "root" -> {
                System.out.flush();
                System.out.printf(
                    """
                                1. Debugging: %s.
                                2. Autotests: %s.
                                3. Read drone data base from file.
                                4. Write drone data base in file.
                                5. Add random drone.
                                6. Remove random drone.
                                0. Exit.
                                """, user.isDebugging(), user.isAutotests()
            );}
        }
    }
    public static void printGreeting(String name) {
        System.out.printf("Welcome, %s!%n", name);
    }
}
