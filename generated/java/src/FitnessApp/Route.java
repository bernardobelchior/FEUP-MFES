package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Route {
  public static final Number EARTH_RADIUS = 6373L;
  private VDMSeq points = SeqUtil.seq();
  private String name;

  public Route(final String n, final VDMSeq ps) {

    throw new UnsupportedOperationException();
  }

  public Number getDistance() {

    throw new UnsupportedOperationException();
  }

  public Route() {}

  public String toString() {

    return "Route{"
        + "EARTH_RADIUS = "
        + Utils.toString(EARTH_RADIUS)
        + ", points := "
        + Utils.toString(points)
        + ", name := "
        + Utils.toString(name)
        + "}";
  }
}
