package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Challenge {
  public String name;
  public String description;
  public Types.Date startDate;
  public Types.Date endDate;
  private VDMSeq completed = SeqUtil.seq();
  public Number goal;

  public void cg_init_Challenge_1(
      final String n, final String d, final Types.Date s, final Types.Date e, final Number g) {

    name = n;
    description = d;
    startDate = Utils.copy(s);
    endDate = Utils.copy(e);
    goal = g;
  }

  public Challenge(
      final String n, final String d, final Types.Date s, final Types.Date e, final Number g) {

    cg_init_Challenge_1(n, d, Utils.copy(s), Utils.copy(e), g);
  }

  public void verifyChallenge(final User user, final Number curVal) {

    if (curVal.doubleValue() >= goal.doubleValue()) {
      addCompletedUser(user);
    }
  }

  public void addCompletedUser(final User u) {

    completed = SeqUtil.conc(SeqUtil.seq(u), Utils.copy(completed));
  }

  public VDMSeq getCompleted() {

    return Utils.copy(completed);
  }

  public Challenge() {}

  public String toString() {

    return "Challenge{"
        + "name := "
        + Utils.toString(name)
        + ", description := "
        + Utils.toString(description)
        + ", startDate := "
        + Utils.toString(startDate)
        + ", endDate := "
        + Utils.toString(endDate)
        + ", completed := "
        + Utils.toString(completed)
        + ", goal := "
        + Utils.toString(goal)
        + "}";
  }
}
