import FitnessApp.Challenge;
import FitnessApp.FitnessApp;
import FitnessApp.Types.Date;
import FitnessApp.Types.DateTime;
import FitnessApp.Types.Point;
import FitnessApp.Types.Time;
import FitnessApp.User;
import FitnessApp.Workout;

import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.Callable;

import org.overture.codegen.runtime.VDMSeq;
import org.overture.codegen.runtime.VDMSet;

public class CommandLineInterface {

	private static final int EMPTY_LINES = 10;

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
		printEmptyLines(EMPTY_LINES);
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
		printEmptyLines(EMPTY_LINES);
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

	public void challengesMenu() {
		printEmptyLines(EMPTY_LINES);
		printLine();
		System.out.println("Challenges Menu");
		ArrayList<SimpleEntry<String, Callable<Void>>> challengesMenuEntries = new ArrayList<>();

		while (true) {
			challengesMenuEntries.clear();
			addChallengesMenuEntries(challengesMenuEntries);
			printMenuEntries(challengesMenuEntries);
			int option = getUserInput(1, challengesMenuEntries.size() - 1);

			try {
				challengesMenuEntries.get(option).getValue().call();
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
			loggedInMenuEntries.add(new SimpleEntry<>("Challenges", () -> {
				challengesMenu();
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
				viewUserWorkoutsMenu();
				return null;
			}));
			workoutsMenuEntries.add(new SimpleEntry<>("Start New Workout", () -> {
				startNewWorkoutMenu();
				return null;
			}));
		}
		else {
			mainMenu();
		}
	}
	
	private void addChallengesMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> challengeMenuEntries) {
		if (fitnessApp.isLoggedIn()) {
			challengeMenuEntries.add(new SimpleEntry<>("View Challenges", () -> {
				viewChallengesMenu();
				return null;
			}));
			challengeMenuEntries.add(new SimpleEntry<>("Add Challenge", () -> {
				startNewWorkoutMenu();
				return null;
			}));
		}
		else {
			mainMenu();
		}
	}

	private void createAccountMenu() {
		printEmptyLines(EMPTY_LINES);
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
		System.out.print("Weight: ");
		double weight = Double.parseDouble(reader.nextLine());
		System.out.print("Height: ");
		double height = Double.parseDouble(reader.nextLine());
		fitnessApp.addUser(new User(firstName, lastName, email, password, weight, height));
	}

	private void loginMenu() {
		printEmptyLines(EMPTY_LINES);
		printLine();
		System.out.println("Login menu");
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

	private void viewUserWorkoutsMenu() {
		printEmptyLines(EMPTY_LINES);

		User loggedInUser = fitnessApp.getLoggedInUser();
		VDMSet userWorkouts = loggedInUser.getWorkouts();

		if (userWorkouts.size() == 0) {
			System.out.println("No Workouts :(");
			System.out.println("Enter to continue");
			reader.nextLine();
			return;
		}

		Iterator<Workout> it = userWorkouts.iterator();
		int i = 1;
		while (it.hasNext()) {
			Workout workout = it.next();
			System.out.println(i + ": " + workout.getTitle());
			System.out.println("  Duration: " + workout.getDuration() + " min");
			System.out.println("  Distance: " + workout.getDistance() + " km");
			System.out.println("  Average Rhythm: " + workout.getAverageRhythm() + " min/km");
			System.out.println("  Calories Burned: " + workout.getCaloriesBurned() + " kcal");
			i++;
		}

		printEmptyLines(EMPTY_LINES);
	}
	
	private void viewChallengesMenu() {
		printEmptyLines(EMPTY_LINES);
		
		VDMSeq challenges = fitnessApp.getChallenges();
		
		if (challenges.size() == 0) {
			System.out.println("No Challenges :(");
			System.out.println("Enter to continue");
			reader.nextLine();
			return;
		}
		
		Iterator<Challenge> it = challenges.iterator();
		int i = 1;
		while (it.hasNext()) {
			Challenge challenge = it.next();
			System.out.println(i + ": " + challenge.getName());
			int activity = challenge.getTypeOfChallenge().intValue();
			if(activity == 0) { // Running
				System.out.println("   Running " + challenge.getGoal() + " km");
			}
			else if(activity == 1) { // Number of calories
				System.out.println("   Burning " + challenge.getGoal() + " kcal");
			}
			else if(activity == 2) { // Time
				System.out.println("   Do exercise for " + challenge.getGoal() + " min");
			}
			i++;
		}

		printEmptyLines(EMPTY_LINES);
	}

	private void startNewWorkoutMenu() {
		printEmptyLines(EMPTY_LINES);

		User loggedInUser = fitnessApp.getLoggedInUser();

		System.out.print("Workout Name: ");
		String workoutName = reader.nextLine();
		System.out.print("Activity Type (Bycicle, Running, Walking): ");
		String activityType = reader.nextLine();
		System.out.println("First point (latitude, longitude)");
		System.out.print("Latitude: ");
		int initialLatitude = Integer.parseInt(reader.nextLine());
		System.out.print("Longitude: ");
		int initialLongitude = Integer.parseInt(reader.nextLine());

		LocalDateTime initialDate = LocalDateTime.now();
		DateTime dateTime = new DateTime(new Date(initialDate.getYear(), initialDate.getMonth().getValue(), initialDate.getDayOfMonth()),
				new Time(initialDate.getHour(), initialDate.getMinute(), initialDate.getSecond()));

		Workout newWorkout = new Workout(workoutName, dateTime, activityType, new Point(initialLatitude, initialLongitude)); 

		while (true) {
			System.out.println("Next Point (latitude, longitude) OR s to stop");

			System.out.print("Latitude: ");
			String firstInput = reader.nextLine();
			if(firstInput.equals("s")) {
				break;
			}
			int latitude = Integer.parseInt(firstInput);

			System.out.print("Longitude: ");
			int longitude = Integer.parseInt(reader.nextLine());
			newWorkout.addPoint(new Point(latitude, longitude));
		}
		
		LocalDateTime endDate = LocalDateTime.now();
		double duration = 0;
		
		if(initialDate.getMinute() == endDate.getMinute()) {
			duration = (endDate.getSecond() - initialDate.getSecond()) / 60.0;
		}
		else if(initialDate.getHour() == endDate.getHour()) {
			duration = endDate.getMinute() - initialDate.getMinute() + (endDate.getSecond() + (60 - initialDate.getSecond())) / 60.0;
		}
		else {
			duration = (60 - initialDate.getMinute()) + endDate.getMinute() + (endDate.getSecond() + (60 - initialDate.getSecond())) / 60.0;
		}
		
		newWorkout.endWorkout(loggedInUser, duration);
		loggedInUser.addWorkout(newWorkout);
		printEmptyLines(EMPTY_LINES);
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

	public void printEmptyLines(int linesToPrint) {
		for(int i=0; i < linesToPrint; i++) {
			System.out.println();
		}
	}
}
