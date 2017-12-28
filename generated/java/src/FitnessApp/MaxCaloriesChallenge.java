package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MaxCaloriesChallenge extends Challenge {
  public Number caloriesChallenged;

  public MaxCaloriesChallenge(
      final String n, final String d, final Types.Date s, final Types.Date e, final Number c) {

    throw new UnsupportedOperationException();
  }

  public Number getCaloriesChallenged() {

    throw new UnsupportedOperationException();
  }

  public void verifyChallenge(final User user, final Number currentCalories) {

    throw new UnsupportedOperationException();
  }

  public MaxCaloriesChallenge() {}

  public String toString() {

    return "MaxCaloriesChallenge{"
        + "caloriesChallenged := "
        + Utils.toString(caloriesChallenged)
        + "}";
  }
}
