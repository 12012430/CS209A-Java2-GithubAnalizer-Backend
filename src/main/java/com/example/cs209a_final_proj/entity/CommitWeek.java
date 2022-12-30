package com.example.cs209a_final_proj.entity;

public class CommitWeek {

  private int weekday;
  private int weekend;

  public CommitWeek(int weekday, int weekend) {
    this.weekday = weekday;
    this.weekend = weekend;
  }

  public CommitWeek() {
  }

  public void setWeekday(int weekday) {
    this.weekday = weekday;
  }

  public void setWeekend(int weekend) {
    this.weekend = weekend;
  }

  public int getWeekday() {
    return weekday;
  }

  public int getWeekend() {
    return weekend;
  }

  @Override
  public String toString() {
    return "{" +
        "weekday=" + weekday +
        ", weekend=" + weekend +
        '}';
  }
}
