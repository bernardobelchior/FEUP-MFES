package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Goal {
  private Number distance;

  public Goal(final Number d) {

    throw new UnsupportedOperationException();
  }

  public Goal() {}

  public String toString() {

    return "Goal{" + "distance := " + Utils.toString(distance) + "}";
  }
}
