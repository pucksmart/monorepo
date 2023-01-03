package dev.pucksmart;

public class TimeUtils {
  public static int secondsFromClockTime(String clockTime) {
    String[] parts = clockTime.split(":", 2);
    return (Integer.parseInt(parts[0]) * 60) + Integer.parseInt(parts[1]);
  }
}
