package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Goal {
  private Number distance;

  public void cg_init_Goal_1(final Number d) {

    distance = d;
  }

  public Goal(final Number d) {

    cg_init_Goal_1(d);
  }

  public Goal() {}

  public String toString() {

    return "Goal{" + "distance := " + Utils.toString(distance) + "}";
  }
}
