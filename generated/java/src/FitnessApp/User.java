package FitnessApp;

import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.Utils;
import org.overture.codegen.runtime.VDMSet;

@SuppressWarnings("all")
public class User {
  private String firstName;
  private String lastName;
  private VDMSet workouts = SetUtil.set();
  private VDMSet myRoutes = SetUtil.set();
  private VDMSet goals = SetUtil.set();
  private VDMSet friends = SetUtil.set();
  private String email;
  private String password;
  public Object gender;
  public Number weight = null;
  public Number height = null;

  public void cg_init_User_1(
      final String fName, final String lName, final String mail, final String passw) {

    firstName = fName;
    lastName = lName;
    email = mail;
    password = passw;
  }

  public User(final String fName, final String lName, final String mail, final String passw) {

    cg_init_User_1(fName, lName, mail, passw);
  }

  public void addRoute(final Route route) {

    myRoutes = SetUtil.union(Utils.copy(myRoutes), SetUtil.set(route));
  }

  public void addWorkout(final Workout workout) {

    workouts = SetUtil.union(Utils.copy(workouts), SetUtil.set(workout));
  }

  public void addGoal(final Goal goal) {

    goals = SetUtil.union(Utils.copy(goals), SetUtil.set(goal));
  }

  public void addFriend(final User f) {

    friends = SetUtil.union(Utils.copy(friends), SetUtil.set(f));
  }

  public VDMSet getWorkouts() {

    return Utils.copy(workouts);
  }

  public String getFirstName() {

    return firstName;
  }

  public String getLastName() {

    return lastName;
  }

  public VDMSet getMyRoutes() {

    return Utils.copy(myRoutes);
  }

  public VDMSet getFriends() {

    return Utils.copy(friends);
  }

  public VDMSet getGoals() {

    return Utils.copy(goals);
  }

  public String getEmail() {

    return email;
  }

  public String getPassword() {

    return password;
  }

  public Boolean checkLogin(final String mail, final String passw) {

    Boolean andResult_28 = false;

    if (Utils.equals(email, mail)) {
      if (Utils.equals(password, passw)) {
        andResult_28 = true;
      }
    }

    return andResult_28;
  }

  public User() {}

  public String toString() {

    return "User{"
        + "firstName := "
        + Utils.toString(firstName)
        + ", lastName := "
        + Utils.toString(lastName)
        + ", workouts := "
        + Utils.toString(workouts)
        + ", myRoutes := "
        + Utils.toString(myRoutes)
        + ", goals := "
        + Utils.toString(goals)
        + ", friends := "
        + Utils.toString(friends)
        + ", email := "
        + Utils.toString(email)
        + ", password := "
        + Utils.toString(password)
        + ", gender := "
        + Utils.toString(gender)
        + ", weight := "
        + Utils.toString(weight)
        + ", height := "
        + Utils.toString(height)
        + "}";
  }
}
