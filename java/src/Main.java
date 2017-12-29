import FitnessApp.FitnessApp;

@SuppressWarnings("all")
public class Main {
  public static void main(String[] args) {
    FitnessApp fitnessApp = new FitnessApp();
    CommandLineInterface cli = new CommandLineInterface(fitnessApp);
    cli.mainMenu();
  }
}
