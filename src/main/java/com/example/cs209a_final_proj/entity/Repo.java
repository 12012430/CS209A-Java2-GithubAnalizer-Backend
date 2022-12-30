package com.example.cs209a_final_proj.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Repo {

  private int repo_id;
  ;
  private String repo_name;

  private int total_developers;
  private int total_open_issues;

  private int total_closed_issues;
  private int total_releases;

  private float issue_resolution_time;

  public Repo(int repo_id, String repo_name, int total_developers, int total_open_issues,
      int total_closed_issues, int total_releases, float issue_resolution_time) {
    this.repo_id = repo_id;
    this.repo_name = repo_name;
    this.total_developers = total_developers;
    this.total_open_issues = total_open_issues;
    this.total_closed_issues = total_closed_issues;
    this.total_releases = total_releases;
    this.issue_resolution_time = issue_resolution_time;
  }

  public Repo() {
  }

  public int getRepoId() {
    return repo_id;
  }

  public void setRepoId(int repo_id) {
    this.repo_id = repo_id;
  }

  public String getRepoName() {
    return repo_name;
  }

  public void setRepoName(String repo_name) {
    this.repo_name = repo_name;
  }

  public int getTotalDevelopers() {
    return total_developers;
  }

  public void setTotalDevelopers(int total_developers) {
    this.total_developers = total_developers;
  }

  public int getTotalOpenIssues() {
    return total_open_issues;
  }

  public void setTotalOpenIssues(int total_open_issues) {
    this.total_open_issues = total_open_issues;
  }

  public int getTotalClosedIssues() {
    return total_closed_issues;
  }

  public void setTotalClosedIssues(int total_closed_issues) {
    this.total_closed_issues = total_closed_issues;
  }

  public int getTotalReleases() {
    return total_releases;
  }

  public void setTotalReleases(int total_releases) {
    this.total_releases = total_releases;
  }

  public float getIssueResolutionTime() {
    return issue_resolution_time;
  }

  public void setIssueResolutionTime(float issue_resolution_time) {
    this.issue_resolution_time = issue_resolution_time;
  }


  @Override
  public String toString() {
    return "Repo{" +
        "repo_id=" + repo_id +
        ", repo_name='" + repo_name + '\'' +
        ", total_developers=" + total_developers +
        ", total_open_issues=" + total_open_issues +
        ", total_closed_issues=" + total_closed_issues +
        ", total_releases=" + total_releases +
        ", issue_resolution_time=" + issue_resolution_time +
        '}';
  }
}