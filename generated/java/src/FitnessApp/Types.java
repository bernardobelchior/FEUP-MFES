package FitnessApp;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Types {
  public Types() {}

  public String toString() {

    return "Types{}";
  }

  public static class DateTime implements Record {
    public Date date;
    public Time time;

    public DateTime(final Date _date, final Time _time) {

      date = _date != null ? Utils.copy(_date) : null;
      time = _time != null ? Utils.copy(_time) : null;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof DateTime)) {
        return false;
      }

      DateTime other = ((DateTime) obj);

      return (Utils.equals(date, other.date)) && (Utils.equals(time, other.time));
    }

    public int hashCode() {

      return Utils.hashCode(date, time);
    }

    public DateTime copy() {

      return new DateTime(date, time);
    }

    public String toString() {

      return "mk_Types`DateTime" + Utils.formatFields(date, time);
    }
  }

  public static class Time implements Record {
    public Number hours;
    public Number minutes;
    public Number seconds;

    public Time(final Number _hours, final Number _minutes, final Number _seconds) {

      hours = _hours;
      minutes = _minutes;
      seconds = _seconds;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Time)) {
        return false;
      }

      Time other = ((Time) obj);

      return (Utils.equals(hours, other.hours))
          && (Utils.equals(minutes, other.minutes))
          && (Utils.equals(seconds, other.seconds));
    }

    public int hashCode() {

      return Utils.hashCode(hours, minutes, seconds);
    }

    public Time copy() {

      return new Time(hours, minutes, seconds);
    }

    public String toString() {

      return "mk_Types`Time" + Utils.formatFields(hours, minutes, seconds);
    }
  }

  public static Boolean inv_Time(final Time time) {

    Boolean andResult_8 = false;

    if (time.minutes.longValue() >= 0L) {
      Boolean andResult_9 = false;

      if (time.minutes.longValue() < 60L) {
        Boolean andResult_10 = false;

        if (time.seconds.longValue() >= 0L) {
          Boolean andResult_11 = false;

          if (time.seconds.longValue() < 60L) {
            if (time.hours.longValue() >= 0L) {
              andResult_11 = true;
            }
          }

          if (andResult_11) {
            andResult_10 = true;
          }
        }

        if (andResult_10) {
          andResult_9 = true;
        }
      }

      if (andResult_9) {
        andResult_8 = true;
      }
    }

    return andResult_8;
  }

  public static class Date implements Record {
    public Number year;
    public Number month;
    public Number day;

    public Date(final Number _year, final Number _month, final Number _day) {

      year = _year;
      month = _month;
      day = _day;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(year, other.year))
          && (Utils.equals(month, other.month))
          && (Utils.equals(day, other.day));
    }

    public int hashCode() {

      return Utils.hashCode(year, month, day);
    }

    public Date copy() {

      return new Date(year, month, day);
    }

    public String toString() {

      return "mk_Types`Date" + Utils.formatFields(year, month, day);
    }
  }

  public static Boolean inv_Date(final Date date) {

    Boolean andResult_16 = false;

    if (date.year.longValue() >= 0L) {
      Boolean andResult_17 = false;

      if (date.month.longValue() > 0L) {
        Boolean andResult_18 = false;

        if (date.month.longValue() <= 12L) {
          Boolean andResult_19 = false;

          if (date.day.longValue() > 0L) {
            if (date.day.longValue() <= 31L) {
              andResult_19 = true;
            }
          }

          if (andResult_19) {
            andResult_18 = true;
          }
        }

        if (andResult_18) {
          andResult_17 = true;
        }
      }

      if (andResult_17) {
        andResult_16 = true;
      }
    }

    return andResult_16;
  }

  public static class Point implements Record {
    public Number lat;
    public Number long_;

    public Point(final Number _lat, final Number _long_) {

      lat = _lat;
      long_ = _long_;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Point)) {
        return false;
      }

      Point other = ((Point) obj);

      return (Utils.equals(lat, other.lat)) && (Utils.equals(long_, other.long_));
    }

    public int hashCode() {

      return Utils.hashCode(lat, long_);
    }

    public Point copy() {

      return new Point(lat, long_);
    }

    public String toString() {

      return "mk_Types`Point" + Utils.formatFields(lat, long_);
    }
  }
}
