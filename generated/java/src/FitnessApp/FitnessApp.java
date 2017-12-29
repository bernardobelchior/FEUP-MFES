package FitnessApp;

import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.Utils;
import org.overture.codegen.runtime.VDMSet;

@SuppressWarnings("all")
public class FitnessApp {
  private VDMSet users = SetUtil.set();
  private User loggedInUser = null;

  public FitnessApp(final VDMSet u) {

    throw new UnsupportedOperationException();
  }

  public void addUser(final User u) {

    throw new UnsupportedOperationException();
  }

  public Boolean isValidUser(final User u) {

    throw new UnsupportedOperationException();
  }

  public Boolean isLoggedIn() {

    throw new UnsupportedOperationException();
  }

  public void logout() {

    throw new UnsupportedOperationException();
  }

  public FitnessApp() {}

  public String toString() {

    return "FitnessApp{"
        + "users := "
        + Utils.toString(users)
        + ", loggedInUser := "
        + Utils.toString(loggedInUser)
        + "}";
  }
}
