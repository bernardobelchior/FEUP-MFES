package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MaxDistanceChallenge extends Challenge {
  public Number distanceChallenged;

  public MaxDistanceChallenge(
      final String n, final String d, final Types.Date s, final Types.Date e, final Number dc) {

    throw new UnsupportedOperationException();
  }

  public Number getDistanceChallenged() {

    throw new UnsupportedOperationException();
  }

  public void verifyChallenge(final User user, final Number currentDistance) {

    throw new UnsupportedOperationException();
  }

  public MaxDistanceChallenge() {}

  public String toString() {

    return "MaxDistanceChallenge{"
        + "distanceChallenged := "
        + Utils.toString(distanceChallenged)
        + "}";
  }
}
