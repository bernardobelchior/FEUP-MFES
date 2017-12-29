import FitnessApp.FitnessApp;
import FitnessApp.User;
import FitnessApp.Workout;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.Callable;

import org.overture.codegen.runtime.VDMSet;

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

	public void loggedInMenu() {
		printLine();
		System.out.println("Logged In Menu");
		ArrayList<SimpleEntry<String, Callable<Void>>> loggedInMenuEntries = new ArrayList<>();
		
		while (true) {
			loggedInMenuEntries.clear();
			addLoggedInMenuEntries(loggedInMenuEntries);
			printMenuEntries(loggedInMenuEntries);
			int option = getUserInput(1, loggedInMenuEntries.size() - 1);

			try {
				loggedInMenuEntries.get(option).getValue().call();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void userWorkoutsMenu() {
		printLine();
		System.out.println("Workouts Menu");
		ArrayList<SimpleEntry<String, Callable<Void>>> workoutsMenuEntries = new ArrayList<>();

		while (true) {
			workoutsMenuEntries.clear();
			addWorkoutsMenuEntries(workoutsMenuEntries);
			printMenuEntries(workoutsMenuEntries);
			int option = getUserInput(1, workoutsMenuEntries.size() - 1);

			try {
				workoutsMenuEntries.get(option).getValue().call();
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

	private void addLoggedInMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> loggedInMenuEntries) {
		if (fitnessApp.isLoggedIn()) {
			loggedInMenuEntries.add(new SimpleEntry<>("My Workouts", () -> {
				userWorkoutsMenu();
				return null;
			}));
		}
		else {
			mainMenu();
		}
	}

	private void addWorkoutsMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> workoutsMenuEntries) {
		if (fitnessApp.isLoggedIn()) {
			workoutsMenuEntries.add(new SimpleEntry<>("View My Workouts", () -> {
				viewUserWorkouts();
				return null;
			}));
			workoutsMenuEntries.add(new SimpleEntry<>("Add Workout", () -> {
				// addNewUserWorkoutMenu();
				return null;
			}));
		}
		else {
			mainMenu();
		}
	}

	private void createAccountMenu() {
		printLine();
		System.out.println("Create account menu");
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
		if (fitnessApp.login(email, password)) {
			loggedInMenu();
		}
		else {
			System.out.println("Login has fail");
		}
	}

	private void viewUserWorkouts() {
		User loggedInUser = fitnessApp.getLoggedInUser();
		VDMSet userWorkouts = loggedInUser.getWorkouts();
		
		if (userWorkouts.size() == 0) {
			System.out.println("No Workouts :(");
			System.out.println("Enter to continue");
			reader.nextLine();
		}

		Iterator<Workout> it = userWorkouts.iterator();
		int i = 1;
		while (it.hasNext()) {
			System.out.print(i + ": " + it.next().getTitle());
			i++;
		}
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
		int option = Integer.parseInt(reader.nextLine());
		
		if (option < bottomBound && option > upperBound) {
			System.out.println("Invalid option");
			option = getUserInput(bottomBound, upperBound);
		}

		return option - 1;
	}
}
