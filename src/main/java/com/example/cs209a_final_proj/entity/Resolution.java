package com.example.cs209a_final_proj.entity;

public class Resolution {
    int three;
    int week;
    int month;
    int more;

    public Resolution(int three, int week, int month, int more) {
        this.three = three;
        this.week = week;
        this.month = month;
        this.more = more;
    }

    public Resolution() {
    }

    public void setThree(int three) {
        this.three = three;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public int getThree() {
        return three;
    }

    public int getWeek() {
        return week;
    }

    public int getMonth() {
        return month;
    }

    public int getMore() {
        return more;
    }

    @Override
    public String toString() {
        return "{" +
                "three=" + three +
                ", week=" + week +
                ", month=" + month +
                ", more=" + more +
                '}';
    }
}
