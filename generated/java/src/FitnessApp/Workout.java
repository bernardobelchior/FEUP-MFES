package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Workout {
  private Number distance = 0L;
  private Number caloriesBurned = 0L;
  private Number averageRhythm = 0L;
  private Types.Time time = new Types.Time(0L, 0L, 0L);
  private Types.DateTime startDateTime;
  private Object activity;
  private VDMSeq points = SeqUtil.seq();
  private String title;
  public Route route = null;
  public String description;

  public void cg_init_Workout_1(
      final String t, final Types.DateTime sd, final Object a, final Types.Point p) {

    title = t;
    startDateTime = Utils.copy(sd);
    activity = a;
    points = SeqUtil.conc(Utils.copy(points), SeqUtil.seq(Utils.copy(p)));
  }

  public Workout(final String t, final Types.DateTime sd, final Object a, final Types.Point p) {

    cg_init_Workout_1(t, Utils.copy(sd), a, Utils.copy(p));
  }

  public void addPoint(final Types.Point p) {

    points = SeqUtil.conc(Utils.copy(points), SeqUtil.seq(Utils.copy(p)));
  }

  public VDMSeq getPoints() {

    return Utils.copy(points);
  }

  public String getTitle() {

    return title;
  }

  public Workout() {}

  public String toString() {

    return "Workout{"
        + "distance := "
        + Utils.toString(distance)
        + ", caloriesBurned := "
        + Utils.toString(caloriesBurned)
        + ", averageRhythm := "
        + Utils.toString(averageRhythm)
        + ", time := "
        + Utils.toString(time)
        + ", startDateTime := "
        + Utils.toString(startDateTime)
        + ", activity := "
        + Utils.toString(activity)
        + ", points := "
        + Utils.toString(points)
        + ", title := "
        + Utils.toString(title)
        + ", route := "
        + Utils.toString(route)
        + ", description := "
        + Utils.toString(description)
        + "}";
  }
}
