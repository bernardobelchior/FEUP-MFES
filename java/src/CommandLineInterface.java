import FitnessApp.FitnessApp;
import FitnessApp.User;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class CommandLineInterface {

    private Scanner reader = new Scanner(System.in);
    private FitnessApp fitnessApp;

    public CommandLineInterface(FitnessApp fitnessApp) {
        this.fitnessApp = fitnessApp;
    }

    public void mainMenu() {
        printLine();
        System.out.println("Welcome to your favorite Fitness app");
        ArrayList<SimpleEntry<String, Callable<Void>>> mainMenuEntries = new ArrayList<>();


        while (true) {
            mainMenuEntries.clear();
            addMainMenuEntries(mainMenuEntries);
            printMenuEntries(mainMenuEntries);
            int option = getUserInput(1, mainMenuEntries.size() - 1);

            try {
                mainMenuEntries.get(option).getValue().call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addMainMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> mainMenuEntries) {
        if (!fitnessApp.isLoggedIn()) {
            mainMenuEntries.add(new SimpleEntry<>("Create Account", () -> {
                createAccountMenu();
                return null;
            }));
            mainMenuEntries.add(new SimpleEntry<>("Login", () -> {
                loginMenu();
                return null;
            }));
        } else {
            mainMenuEntries.add(new SimpleEntry<>("Logout", () -> {
                fitnessApp.logout();
                loginMenu();
                return null;
            }));
        }

        mainMenuEntries.add(new SimpleEntry<>("Exit", () -> {
            System.exit(0);
            return null;
        }));
    }

    private void createAccountMenu() {
        System.out.println("Create account menu");
        printLine();
        System.out.print("First name: ");
        String firstName = reader.nextLine();
        System.out.print("Last name: ");
        String lastName = reader.nextLine();
        System.out.print("Email: ");
        String email = reader.nextLine();
        System.out.print("Password: ");
        String password = reader.nextLine();
        fitnessApp.addUser(new User(firstName, lastName, email, password));
    }

    private void loginMenu() {
        System.out.println("Login menu");
        printLine();
        System.out.print("Email: ");
        String email = reader.nextLine();
        System.out.print("Password: ");
        String password = reader.nextLine();
        fitnessApp.login(email, password);
    }

    private void printLine() {
        System.out.println("=====================================");
    }

    private void printMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> menuEntries) {
        for (int i = 0; i < menuEntries.size(); i++) {
            System.out.println((i + 1) + ": " + menuEntries.get(i).getKey());
        }
    }

    private int getUserInput(int bottomBound, int upperBound) {
        System.out.println("Choose an option: ");
        int option = reader.nextInt();
        reader.skip("\n");

        if (option < bottomBound && option > upperBound) {
            System.out.println("Invalid option");
            option = getUserInput(bottomBound, upperBound);
        }

        return option - 1;
    }
}
