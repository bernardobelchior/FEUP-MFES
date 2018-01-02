import FitnessApp.Challenge;
import FitnessApp.FitnessApp;
import FitnessApp.Goal;
import FitnessApp.Route;
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

	public void userRoutesMenu() {
		printEmptyLines(EMPTY_LINES);
		printLine();
		System.out.println("Routes Menu");
		ArrayList<SimpleEntry<String, Callable<Void>>> routesMenuEntries = new ArrayList<>();

		while (true) {
			routesMenuEntries.clear();
			addRoutesMenuEntries(routesMenuEntries);
			printMenuEntries(routesMenuEntries);
			int option = getUserInput(1, routesMenuEntries.size() - 1);

			try {
				routesMenuEntries.get(option).getValue().call();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void manageUserFriendsMenu() {
		printEmptyLines(EMPTY_LINES);
		printLine();
		System.out.println("Friends Menu");
		ArrayList<SimpleEntry<String, Callable<Void>>> friendsMenuEntries = new ArrayList<>();

		while (true) {
			friendsMenuEntries.clear();
			addFriendsMenuEntries(friendsMenuEntries);
			printMenuEntries(friendsMenuEntries);
			int option = getUserInput(1, friendsMenuEntries.size() - 1);

			try {
				friendsMenuEntries.get(option).getValue().call();
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
			loggedInMenuEntries.add(new SimpleEntry<>("Challenges", () -> {
				challengesMenu();
				return null;
			}));
			loggedInMenuEntries.add(new SimpleEntry<>("My Workouts", () -> {
				userWorkoutsMenu();
				return null;
			}));
			loggedInMenuEntries.add(new SimpleEntry<>("My Routes", () -> {
				userRoutesMenu();
				return null;
			}));
			loggedInMenuEntries.add(new SimpleEntry<>("Manage Friends", () -> {
				manageUserFriendsMenu();
				return null;
			}));
			loggedInMenuEntries.add(new SimpleEntry<>("Logout", () -> {
				fitnessApp.logout();
				printEmptyLines(EMPTY_LINES);
				mainMenu();
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
			workoutsMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
				loggedInMenu();
				return null;
			}));
		}
		else {
			mainMenu();
		}
	}

	private void addRoutesMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> routesMenuEntries) {
		if (fitnessApp.isLoggedIn()) {
			routesMenuEntries.add(new SimpleEntry<>("View My Routes", () -> {
				viewUserRoutesMenu();
				return null;
			}));
			routesMenuEntries.add(new SimpleEntry<>("Create New Route", () -> {
				createNewRouteMenu();
				return null;
			}));
			routesMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
				loggedInMenu();
				return null;
			}));
		}
		else {
			mainMenu();
		}
	}

	private void addFriendsMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> friendsMenuEntries) {
		if (fitnessApp.isLoggedIn()) {
			friendsMenuEntries.add(new SimpleEntry<>("View My Friends", () -> {
				viewUserFriendsMenu();
				return null;
			}));
			friendsMenuEntries.add(new SimpleEntry<>("Add New Friend", () -> {
				addNewFriendMenu();
				return null;
			}));
			friendsMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
				loggedInMenu();
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
				createNewChallengeMenu();
				return null;
			}));
			challengeMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
				loggedInMenu();
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
		printEmptyLines(EMPTY_LINES);
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
		printEmptyLines(EMPTY_LINES);
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

	private void viewUserRoutesMenu() {
		printEmptyLines(EMPTY_LINES);

		User loggedInUser = fitnessApp.getLoggedInUser();
		VDMSet userRoutes = loggedInUser.getMyRoutes();

		if (userRoutes.size() == 0) {
			System.out.println("No Routes :(");
			System.out.println("Enter to continue");
			reader.nextLine();
			return;
		}

		Iterator<Route> it = userRoutes.iterator();
		int i = 1;
		while (it.hasNext()) {
			Route route= it.next();
			System.out.println(i + ": " + route.getName());
			System.out.println("   Route Distance: " + route.getDistance());

			System.out.println("   Points:");
			VDMSeq points = route.getPoints();
			Iterator<Point> ite = points.iterator();
			int j = 1;
			while (ite.hasNext()) {
				Point point = ite.next();
				System.out.println("      " + j + "º Point (Latitude, Longitude): " + point.lat + "," + point.long_);
				j++;
			}
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
			VDMSeq usersCompleted = challenge.getCompleted();
			Iterator<User> ite = usersCompleted.iterator();
			if (usersCompleted.size() > 0) {
				System.out.print("Completed by: ");
			}
			while (ite.hasNext()) {
				System.out.println(ite.next().getFirstName() + " ");
			}
			System.out.println();
		}

		printEmptyLines(EMPTY_LINES);
	}
	
	private void viewUserFriendsMenu() {
		printEmptyLines(EMPTY_LINES);
		
		VDMSet userFriends = fitnessApp.getLoggedInUser().getFriends();
		
		if (userFriends.size() == 0) {
			System.out.println("You haven't added any friends yet:(");
			System.out.println("Enter to continue");
			reader.nextLine();
			return;
		}

		Iterator<User> it = userFriends.iterator();
		int i = 1;
		while (it.hasNext()) {
			User friend = it.next();
			System.out.println(i + ": " + friend.getFirstName() + " " + friend.getLastName() + " with email " + friend.getEmail());
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

		VDMSeq challenges = fitnessApp.getChallenges();

		Iterator<Challenge> it = challenges.iterator();
		int i = 1;
		while (it.hasNext()) {
			Challenge challenge = it.next();
			if (challenge.verifyChallenge(newWorkout)) {
				challenge.addCompletedUser(loggedInUser);
				System.out.println("You completed challenge with name " + challenge.getName());
			}
		}

		printEmptyLines(EMPTY_LINES);
	}

	private void createNewRouteMenu() {
		printEmptyLines(EMPTY_LINES);

		User loggedInUser = fitnessApp.getLoggedInUser();

		System.out.print("Route Name: ");
		String routeName = reader.nextLine();

		VDMSeq points = new VDMSeq();

		System.out.println("First point (latitude, longitude)");
		System.out.print("Latitude: ");
		int initialLatitude = Integer.parseInt(reader.nextLine());
		System.out.print("Longitude: ");
		int initialLongitude = Integer.parseInt(reader.nextLine());

		points.add(new Point(initialLatitude, initialLongitude));

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
			points.add(new Point(latitude, longitude));
		}

		Route newRoute = new Route(routeName, points);
		loggedInUser.addRoute(newRoute);

		printEmptyLines(EMPTY_LINES);
	}

	private void addNewFriendMenu() {
		printEmptyLines(EMPTY_LINES);

		User loggedInUser = fitnessApp.getLoggedInUser();

		System.out.print("New Friend Email: ");
		String friendName = reader.nextLine();
		
		VDMSet users = fitnessApp.getUsers();
		
		Iterator<User> it = users.iterator();
		int i = 1;
		boolean found = false;
		while (it.hasNext()) {
			User user = it.next();
			if (user.getEmail().equals(friendName) && !loggedInUser.equals(user)) {
				loggedInUser.addFriend(user);
				System.out.println("Friend Added");
				found = true;
				break;
			}
		}
		
		if (!found) {
			System.out.println("User Not Found!");
		}

		printEmptyLines(EMPTY_LINES);
	}

	private void createNewChallengeMenu() {
		printEmptyLines(EMPTY_LINES);

		System.out.print("Challenge Name: ");
		String challengeName = reader.nextLine();
		System.out.print("Challenge description: ");
		String challengeDescription = reader.nextLine();
		System.out.print("End Date");
		System.out.print("Year: ");
		int endYear = Integer.parseInt(reader.nextLine());
		System.out.print("Month: ");
		int endMonth = Integer.parseInt(reader.nextLine());
		System.out.print("Day: ");
		int endDay = Integer.parseInt(reader.nextLine());
		System.out.print("Type of Activity (0 -> distance(km) | 1 -> number of calories(kcal) | 2 -> time(min)): ");
		int typeOfActivity = Integer.parseInt(reader.nextLine());
		System.out.print("Challenge Goal: ");
		int challengeGoal = Integer.parseInt(reader.nextLine());

		LocalDateTime initialDate = LocalDateTime.now();

		Challenge newChallenge = new Challenge(challengeName, challengeDescription, 
				new Date(initialDate.getYear(), initialDate.getMonth().getValue(), initialDate.getDayOfMonth()),
				new Date(endYear, endMonth, endDay), challengeGoal, typeOfActivity);

		fitnessApp.addChallenge(newChallenge);

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
