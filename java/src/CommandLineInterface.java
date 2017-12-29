import FitnessApp.FitnessApp;

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

        if(!fitnessApp.isLoggedIn()) {
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

        printMenuEntries(mainMenuEntries);
        int option = getUserInput(1, mainMenuEntries.size() - 1);

        try {
            mainMenuEntries.get(option).getValue().call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createAccountMenu() {
        System.out.println("Create account");
    }

    private void loginMenu() {
        System.out.println("Login");
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

        if (option < bottomBound && option > upperBound) {
            System.out.println("Invalid option");
            option = getUserInput(bottomBound, upperBound);
        }

        return option;
    }
}
