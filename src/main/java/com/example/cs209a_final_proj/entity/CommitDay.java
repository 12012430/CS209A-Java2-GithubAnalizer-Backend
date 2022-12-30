package com.example.cs209a_final_proj.entity;

public class CommitDay {

  private int morning;
  private int afternoon;
  private int evening;

  public CommitDay(int morning, int afternoon, int evening) {
    this.morning = morning;
    this.afternoon = afternoon;
    this.evening = evening;
  }

  public CommitDay() {
  }

  public void setMorning(int morning) {
    this.morning = morning;
  }

  public void setAfternoon(int afternoon) {
    this.afternoon = afternoon;
  }

  public void setEvening(int evening) {
    this.evening = evening;
  }

  public int getMorning() {
    return morning;
  }

  public int getAfternoon() {
    return afternoon;
  }

  public int getEvening() {
    return evening;
  }

  @Override
  public String toString() {
    return "{" +
        "morning=" + morning +
        ", afternoon=" + afternoon +
        ", evening=" + evening +
        '}';
  }
}

