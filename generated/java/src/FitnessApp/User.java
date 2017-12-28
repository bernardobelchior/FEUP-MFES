package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  private String firstName;
  private String lastName;
  private VDMSet workouts = SetUtil.set();
  private VDMSet myRoutes = SetUtil.set();
  private VDMSet goals = SetUtil.set();
  private VDMSet friends = SetUtil.set();
  public Object gender;
  public Number weight = null;
  public Number height = null;

  public User(final String fName, final String lName) {

    throw new UnsupportedOperationException();
  }

  public void addRoute(final Route route) {

    throw new UnsupportedOperationException();
  }

  public void addWorkout(final Workout workout) {

    throw new UnsupportedOperationException();
  }

  public void addGoal(final Goal goal) {

    throw new UnsupportedOperationException();
  }

  public void addFriend(final User f) {

    throw new UnsupportedOperationException();
  }

  public VDMSet getWorkouts() {

    throw new UnsupportedOperationException();
  }

  public String getFirstName() {

    throw new UnsupportedOperationException();
  }

  public String getLastName() {

    throw new UnsupportedOperationException();
  }

  public VDMSet getMyRoutes() {

    throw new UnsupportedOperationException();
  }

  public VDMSet getFriends() {

    throw new UnsupportedOperationException();
  }

  public VDMSet getGoals() {

    throw new UnsupportedOperationException();
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
        + ", gender := "
        + Utils.toString(gender)
        + ", weight := "
        + Utils.toString(weight)
        + ", height := "
        + Utils.toString(height)
        + "}";
  }
}
