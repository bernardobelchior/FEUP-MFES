import FitnessApp.*;
import FitnessApp.Types.Date;
import FitnessApp.Types.DateTime;
import FitnessApp.Types.Point;
import FitnessApp.Types.Time;
import org.overture.codegen.runtime.VDMSeq;
import org.overture.codegen.runtime.VDMSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class CommandLineInterface {

    private static final int EMPTY_LINES = 20;

    private Scanner reader = new Scanner(System.in);
    private FitnessApp fitnessApp;

    CommandLineInterface(FitnessApp fitnessApp) {
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

    private void loggedInMenu() {
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

    private void userWorkoutsMenu() {
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

    private void userRoutesMenu() {
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

    private void manageUserFriendsMenu() {
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

    private void challengesMenu() {
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
        } else {
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
        } else {
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
        } else {
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
        } else {
            mainMenu();
        }
    }

    private void addChallengesMenuEntries(ArrayList<SimpleEntry<String, Callable<Void>>> challengeMenuEntries) {
        if (fitnessApp.isLoggedIn()) {
            challengeMenuEntries.add(new SimpleEntry<>("View Challenges", () -> {
                viewChallengesMenu();
                return null;
            }));
            if (fitnessApp.isAdminLoggedIn())
                challengeMenuEntries.add(new SimpleEntry<>("Add Challenge", () -> {
                    createNewChallengeMenu();
                    return null;
                }));
            challengeMenuEntries.add(new SimpleEntry<>("Main Menu", () -> {
                loggedInMenu();
                return null;
            }));
        } else {
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
        System.out.print("Gender (Masculine, Feminine): ");
        String gender = reader.nextLine();
        fitnessApp.addUser(new User(firstName, lastName, email, password, weight, height, gender));
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
        } else {
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
            Route route = it.next();
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
            System.out.println("  " + challenge.printMessage());
            System.out.println("  Created by: " + challenge.getCreator().getFirstName() + " " + challenge.getCreator().getLastName());

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
        Point p = getPoint();

        LocalDateTime initialDate = LocalDateTime.now();
        DateTime dateTime = new DateTime(new Date(initialDate.getYear(), initialDate.getMonth().getValue(), initialDate.getDayOfMonth()),
                new Time(initialDate.getHour(), initialDate.getMinute(), initialDate.getSecond()));

        Workout newWorkout = new Workout(workoutName, dateTime, activityType, p);

        while (true) {
            System.out.println("Next Point (latitude, longitude) OR s to stop");
            p = getPointOrStop();
            if (p != null) {
                newWorkout.addPoint(p);
            } else {
                break;
            }
        }

        LocalDateTime endDate = LocalDateTime.now();
        double duration;

        if (initialDate.getMinute() == endDate.getMinute()) {
            duration = (endDate.getSecond() - initialDate.getSecond()) / 60.0;
        } else if (initialDate.getHour() == endDate.getHour()) {
            duration = endDate.getMinute() - initialDate.getMinute() + (endDate.getSecond() + (60 - initialDate.getSecond())) / 60.0;
        } else {
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

    /**
     * Gets a Point from the command line.
     *
     * @return {Point}
     */
    private Point getPoint() {
        while (true) {
            try {
                System.out.print("Latitude: ");
                double latitude = Double.parseDouble(reader.nextLine());
                System.out.print("Longitude: ");
                double longitude = Double.parseDouble(reader.nextLine());
                return new Point(latitude, longitude);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again.");
            }
        }
    }

    /**
     * Gets a Point from the command line or null if the user stops the input.
     *
     * @return {Point}
     */
    private Point getPointOrStop() {
        while (true) {
            try {
                System.out.print("Latitude: ");
                String nextLine = reader.nextLine();

                if (nextLine.equals("s")) {
                    return null;
                }

                double latitude = Double.parseDouble(nextLine);

                System.out.print("Longitude: ");
                nextLine = reader.nextLine();
                double longitude = Double.parseDouble(reader.nextLine());

                if (nextLine.equals("s")) {
                    return null;
                }

                return new Point(latitude, longitude);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again.");
            }
        }
    }

    private void createNewRouteMenu() {
        printEmptyLines(EMPTY_LINES);

        User loggedInUser = fitnessApp.getLoggedInUser();

        System.out.print("Route Name: ");
        String routeName = reader.nextLine();

        VDMSeq points = new VDMSeq();

        System.out.println("First point (latitude, longitude)");
        points.add(getPoint());

        while (true) {
            System.out.println("Next Point (latitude, longitude) OR s to stop");

            Point p = getPointOrStop();
            if (p != null) {
                points.add(p);
            } else {
                break;
            }
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

        Admin admin = fitnessApp.getLoggedInAdmin();

        System.out.print("Challenge Name: ");
        String challengeName = reader.nextLine();
        System.out.print("Challenge description: ");
        String challengeDescription = reader.nextLine();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.print("End date (dd-mm-yyy): ");
        java.util.Date endDate;

        try {
            endDate = simpleDateFormat.parse(reader.nextLine());

            if (endDate.compareTo(java.util.Date.from(Instant.now())) <= 0) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            while (true) {
                System.out.println("Invalid date. Please enter end date (dd-mm-yyyy): ");
                try {
                    endDate = simpleDateFormat.parse(reader.nextLine());
                    break;
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        }

        Challenge newChallenge = null;
        boolean validChallenge = false;
        while (!validChallenge) {
            System.out.print("Type of Activity (0 -> distance(km) | 1 -> number of calories(kcal) | 2 -> rhythm(min/km)): ");
            int typeOfActivity = Integer.parseInt(reader.nextLine());

            System.out.print("Challenge Goal: ");
            double challengeGoal = Double.parseDouble(reader.nextLine());

            LocalDateTime initialDate = LocalDateTime.now();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDate);

            switch (typeOfActivity) {
                case 0:
                    newChallenge = new DistanceChallenge(admin, challengeName, challengeDescription,
                            new Date(initialDate.getYear(), initialDate.getMonth().getValue(), initialDate.getDayOfMonth()),
                            new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)), challengeGoal);
                    validChallenge = true;
                    break;
                case 1:
                    newChallenge = new CalorieChallenge(admin, challengeName, challengeDescription,
                            new Date(initialDate.getYear(), initialDate.getMonth().getValue(), initialDate.getDayOfMonth()),
                            new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)), challengeGoal);
                    validChallenge = true;
                    break;
                case 2:
                    newChallenge = new RhythmChallenge(admin, challengeName, challengeDescription,
                            new Date(initialDate.getYear(), initialDate.getMonth().getValue(), initialDate.getDayOfMonth()),
                            new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)), challengeGoal);
                    validChallenge = true;
                    break;
                default:
                    System.out.println("Invalid type of activity. Try again: ");
                    break;
            }
        }

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
        System.out.print("Choose an option: ");
        int option = Integer.parseInt(reader.nextLine());

        if (option < bottomBound && option > upperBound) {
            System.out.println("Invalid option");
            option = getUserInput(bottomBound, upperBound);
        }

        return option - 1;
    }

    private void printEmptyLines(int linesToPrint) {
        for (int i = 0; i < linesToPrint; i++) {
            System.out.println();
        }
    }
}
