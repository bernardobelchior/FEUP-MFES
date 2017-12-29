package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Route {
  public static final Number EARTH_RADIUS = 6373L;
  private VDMSeq points = SeqUtil.seq();
  private String name;

  public void cg_init_Route_1(final String n, final VDMSeq ps) {

    name = n;
    points = Utils.copy(ps);
  }

  public Route(final String n, final VDMSeq ps) {

    cg_init_Route_1(n, Utils.copy(ps));
  }

  public Number getDistance() {

    Number totalDistance = 0L;
    Number upperBound = points.size() - 1L;
    double toVar_1 = upperBound.doubleValue();

    for (Long i = 1L; i <= toVar_1; i++) {
      Number radLat1 =
          Utils.divide(
              MATH.pi.doubleValue() * ((Types.Point) Utils.get(points, i)).lat.doubleValue(), 180L);
      Number radLat2 =
          Utils.divide(
              MATH.pi.doubleValue()
                  * ((Types.Point) Utils.get(points, i.longValue() + 1L)).lat.doubleValue(),
              180L);
      Number theta =
          ((Types.Point) Utils.get(points, i)).long_.doubleValue()
              - ((Types.Point) Utils.get(points, i.longValue() + 1L)).long_.doubleValue();
      Number radTheta = Utils.divide(MATH.pi.doubleValue() * theta.doubleValue(), 180L);
      Number dist =
          MATH.sin(radLat1).doubleValue() * MATH.sin(radLat2).doubleValue()
              + MATH.cos(radLat1).doubleValue()
                  * MATH.cos(radLat2).doubleValue()
                  * MATH.cos(radTheta).doubleValue();
      dist = MATH.acos(dist);
      dist = Utils.divide(dist.doubleValue() * 180L, MATH.pi.doubleValue());
      dist = dist.doubleValue() * 60L * 1.1515;
      dist = dist.doubleValue() * 1.609344;
      totalDistance = totalDistance.doubleValue() + dist.doubleValue();
    }
    return totalDistance;
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
