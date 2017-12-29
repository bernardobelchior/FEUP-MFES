package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FitnessApp {
  private VDMSet users = SetUtil.set();
  private User loggedInUser = null;

  public void cg_init_FitnessApp_1(final VDMSet u) {

    users = Utils.copy(u);
  }

  public FitnessApp(final VDMSet u) {

    cg_init_FitnessApp_1(Utils.copy(u));
  }

  public void addUser(final User u) {

    users = SetUtil.union(SetUtil.set(u), Utils.copy(users));
  }

  public Boolean userExists(final User u) {

    return SetUtil.inSet(u, users);
  }

  public Boolean isLoggedIn() {

    return !(Utils.equals(loggedInUser, null));
  }

  public Boolean login(final String email, final String password) {

    Boolean orResult_2 = false;

    if (email.length() < 5L) {
      orResult_2 = true;
    } else {
      orResult_2 = password.length() < 8L;
    }

    if (orResult_2) {
      return false;
    }

    for (Iterator iterator_1 = users.iterator(); iterator_1.hasNext(); ) {
      User user = (User) iterator_1.next();
      if (user.checkLogin(email, password)) {
        loggedInUser = user;
        return true;
      }
    }
    return false;
  }

  public void logout() {

    loggedInUser = null;
  }

  public User getLoggedInUser() {

    return loggedInUser;
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
