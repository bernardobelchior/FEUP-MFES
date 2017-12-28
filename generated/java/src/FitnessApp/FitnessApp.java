package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FitnessApp {
  private VDMSet users = SetUtil.set();

  public FitnessApp(final VDMSet u) {

    throw new UnsupportedOperationException();
  }

  public void addUser(final User u) {

    throw new UnsupportedOperationException();
  }

  public Boolean isValidUser(final User u) {

    throw new UnsupportedOperationException();
  }

  public FitnessApp() {}

  public String toString() {

    return "FitnessApp{" + "users := " + Utils.toString(users) + "}";
  }
}
