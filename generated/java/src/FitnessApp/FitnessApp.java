package FitnessApp;

import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.Utils;
import org.overture.codegen.runtime.VDMSet;

import java.util.Iterator;

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

  public Boolean isValidUser(final User u) {

    return SetUtil.inSet(u, users);
  }

  public Boolean isLoggedIn() {

    return !(Utils.equals(loggedInUser, null));
  }

  public Boolean login(final String email, final String password) {

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
