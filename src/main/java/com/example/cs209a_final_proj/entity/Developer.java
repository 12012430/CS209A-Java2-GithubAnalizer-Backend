package com.example.cs209a_final_proj.entity;

public class Developer {
    private String name;
    private int commits;

    Developer(String name, int commits) {
        this.name = name;
        this.commits = commits;
    }

    public Developer(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCommits() {
        return commits;
    }

    public void setCommits(int commits) {
        this.commits = commits;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", commits=" + commits +
                '}';
    }
}
