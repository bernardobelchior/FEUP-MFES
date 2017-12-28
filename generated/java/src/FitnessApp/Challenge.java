package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Challenge {
  public String name;
  public String description;
  public Types.Date startDate;
  public Types.Date endDate;
  public VDMSeq completed = SeqUtil.seq();

  public Challenge(final String n, final String d, final Types.Date s, final Types.Date e) {

    throw new UnsupportedOperationException();
  }

  public String getName() {

    throw new UnsupportedOperationException();
  }

  public String getDescription() {

    throw new UnsupportedOperationException();
  }

  public Types.Date getStartDate() {

    throw new UnsupportedOperationException();
  }

  public Types.Date getEndDate() {

    throw new UnsupportedOperationException();
  }

  public VDMSeq getCompleted() {

    throw new UnsupportedOperationException();
  }

  public void addCompletedUser(final User u) {

    throw new UnsupportedOperationException();
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
        + "}";
  }
}
