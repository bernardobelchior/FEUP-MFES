import FitnessApp.Admin;
import FitnessApp.FitnessApp;

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        FitnessApp fitnessApp = new FitnessApp();

        fitnessApp.addAdmin(
                new Admin("Admin", "Admin", "a@d.min", "adminadmin", 70, 1.85, new Object()));

        CommandLineInterface cli = new CommandLineInterface(fitnessApp);
        cli.mainMenu();
    }
}
