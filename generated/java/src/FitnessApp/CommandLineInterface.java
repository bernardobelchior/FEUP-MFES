package FitnessApp;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandLineInterface {

	Scanner reader = new Scanner(System.in);

	public static void main(String args[]) {
		CommandLineInterface cli = new CommandLineInterface();
	}

	public void mainMenu() {
		printLine();
		System.out.println("Welcome to your favorite Fitness app");
		ArrayList<String> mainMenuEntries = new ArrayList();
		mainMenuEntries.add("Login");
		mainMenuEntries.add("Create Account");

		printMenuEntries(mainMenuEntries);
		int option = getUserInput(1, mainMenuEntries.size());

		// TODO add options
		switch (option) {
		// Login
		case 1: 
			break;
		// Create Account
		case 2:
			break;
		default:
			break;
		}
	}

	public void printLine() {
		System.out.println("=====================================");
	}

	public void printMenuEntries(ArrayList<String> menuEntries) {
		for (int i = 1; i <= menuEntries.size(); i++) {
			System.out.println(i + ": " + menuEntries.get(i));
		}
	}

	public int getUserInput(int bottomBound,int upperBound) {
		System.out.println("Choose an option: ");
		int option = reader.nextInt();

		if(option > bottomBound && option > upperBound) {
			System.out.println("Invalid option");
			option = getUserInput(bottomBound, upperBound);
		}

		return option;
	}
}
