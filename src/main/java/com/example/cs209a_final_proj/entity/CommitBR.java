package com.example.cs209a_final_proj.entity;

public class CommitBR {

  private String releaseName;
  private int commits;

  public CommitBR(String release_name, int commits) {
    this.releaseName = release_name;
    this.commits = commits;
  }

  public CommitBR() {
  }

  public void setReleaseName(String release_name) {
    this.releaseName = release_name;
  }

  public void setCommits(int commits) {
    this.commits = commits;
  }

  public String getReleaseName() {
    return releaseName;
  }

  public int getCommits() {
    return commits;
  }

  @Override
  public String toString() {
    return "{" +
        "release_name='" + releaseName + '\'' +
        ", commits=" + commits +
        '}';
  }


}
