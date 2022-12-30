package com.example.cs209a_final_proj.entity;

public class IssueStatus {
    private int open_issues;
    private int closed_issues;

    public IssueStatus(int open_issues, int closed_issues) {
        this.open_issues = open_issues;
        this.closed_issues = closed_issues;
    }

    public IssueStatus() {
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues =open_issues;
    }

    public void setClosed_issues(int closed_issues) {
        this.closed_issues = closed_issues;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public int getClosed_issues() {
        return closed_issues;
    }

    @Override
    public String toString() {
        return "{" +
                "open_issues=" + open_issues +
                ", closed_issues=" + closed_issues +
                '}';
    }

}
