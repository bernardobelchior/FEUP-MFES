package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MaxTimeExerciseChallenge extends Challenge {
  public Number timeChallenged;

  public MaxTimeExerciseChallenge(
      final String n, final String d, final Types.Date s, final Types.Date e, final Number t) {

    throw new UnsupportedOperationException();
  }

  public Number getTimeChallenged() {

    throw new UnsupportedOperationException();
  }

  public void verifyChallenge(final User user, final Number currentTime) {

    throw new UnsupportedOperationException();
  }

  public MaxTimeExerciseChallenge() {}

  public String toString() {

    return "MaxTimeExerciseChallenge{"
        + "timeChallenged := "
        + Utils.toString(timeChallenged)
        + "}";
  }
}
