package com.example.cs209a_final_proj.DataCollect;

import static java.lang.Thread.sleep;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.http.client.fluent.*;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Properties;
import java.sql.*;
import java.net.URL;

import com.google.gson.reflect.TypeToken;

public class Collector {

  private static Connection con = null;
  private static boolean verbose = false;
  private static Properties prop = new Properties();

  private static void openDB(String host, String dbname,
      String user, String pwd) {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
      System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
      System.exit(1);
    }
    String url = "jdbc:postgresql://" + host + "/" + dbname;
    Properties props = new Properties();
    props.setProperty("user", user);
    props.setProperty("password", pwd);
    try {
      con = DriverManager.getConnection(url, props);
      if (verbose) {
        System.out.println("Successfully connected to the database "
            + dbname + " as " + user);
      }
    } catch (SQLException e) {
      System.err.println("Database connection failed");
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  private static int repo_id = 2;

  private static String path = "/alibaba/fastjson";


  private static void closeDB() {
    if (con != null) {
      try {
        con.close();
        con = null;
      } catch (Exception e) {
        System.err.println("Error closing the database connection");
        System.err.println(e.getMessage());
      }
    }
  }

  private static void init_database() {
    prop.put("host", "localhost");
    prop.put("user", "postgres");
    prop.put("password", "123456");
    prop.put("database", "postgres");
  }

  public static void main(String[] args) throws InterruptedException {
    init_database(); // initialize the database
    insertRepo();
    System.out.println("Start collecting data repo_id: " + repo_id + " path: " + path);
    getIssue(1);// store the issue
    getCommit(1);// store the commit
    getRelease(1);// store the release
    addCommitBetweenRelease();// add the commit between release
  }

  private static void getRelease(int page) {
    // Commit (GET )
    try {
      // Create request
      Content content = Request.Get(
              "https://api.github.com/repos" + path + "/releases?per_page=100&page="
                  + String.valueOf(
                  page))
          // Add headers
          .addHeader("Accept", "application/vnd.github+json")
          .addHeader("X-Github-Api-Version", "2022-11-28")
          .addHeader("Authorization",
              "Bearer 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") //Please fill in your own token, this token is invalid since I have revoked it.
          // Fetch request and return content
          .execute().returnContent();
      // Print content
      JsonParser parser = new JsonParser();
      JsonArray jsonArray = parser.parse(content.toString()).getAsJsonArray();
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
      //Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
      int i = 0;
      openDB(prop.getProperty("host"), prop.getProperty("database"),
          prop.getProperty("user"), prop.getProperty("password"));
      for (JsonElement jsonElement : jsonArray) {
        i++;
        Release release = gson.fromJson(jsonElement, Release.class);
        int release_id = release.getId();
        String release_name = release.getName();
        String released_at = release.getPublishedAt();
        //System.out.println(String.valueOf(release_id) + " " + release_name + " " + released_at);
        Statement stmt;
        if (con != null) {
          stmt = con.createStatement();
          stmt.execute(
              "insert into release(repo_id, release_id, release_name, released_at,commit_between_release) values ("
                  + String.valueOf(repo_id) + "," + release_id + ",'" + release_name + "','"
                  + released_at + "',null)");
          stmt.close();
        }

      }
      if (i < 100) {
        closeDB();
        System.out.println("Collect release finished");
        return;
      } else {
        closeDB();
        getRelease(page + 1);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void getCommit(int page) {
    // Commit (GET )
    try {
      // Create request
      Content content = Request.Get(
              "https://api.github.com/repos" + path + "/commits?per_page=100&state=all&page="
                  + String.valueOf(
                  page))
          // Add headers
          .addHeader("Accept", "application/vnd.github+json")
          .addHeader("X-Github-Api-Version", "2022-11-28")
          .addHeader("Authorization",
              "Bearer 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") //Please fill in your own token, this token is invalid since I have revoked it.
          // Fetch request and return content
          .execute().returnContent();
      // Print content
      JsonParser parser = new JsonParser();
      JsonArray jsonArray = parser.parse(content.toString()).getAsJsonArray();
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
      //Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
      int i = 0;
      openDB(prop.getProperty("host"), prop.getProperty("database"),
          prop.getProperty("user"), prop.getProperty("password"));
      for (JsonElement jsonElement : jsonArray) {
        i++;
        Commit commit = gson.fromJson(jsonElement, Commit.class);
        String sha = commit.getSha();
        String commited_at = commit.getCommit().getAuthor().getDate();
        String developer_name = commit.getCommit().getAuthor().getName();

        Statement stmt;
        if (con != null) {
          stmt = con.createStatement();
          stmt.execute("insert into commit(repo_id,sha,committed_at,developer_name) values (" +
              String.valueOf(repo_id) + ",'" + sha + "','" + commited_at + "','" + developer_name
              + "')");
          stmt.close();
        }

      }
      if (i < 100) {
        closeDB();
        System.out.println("Collect commit finished");
        return;
      } else {
        closeDB();
        getCommit(page + 1);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void getIssue(int page) {
    // Issue (GET )
    try {
      // Create request
      Content content = Request.Get(
              "https://api.github.com/repos" + path + "/issues?per_page=100&state=all&page="
                  + String.valueOf(
                  page))
          // Add headers
          .addHeader("Accept", "application/vnd.github+json")
          .addHeader("Authorization",
              "Bearer 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") //Please fill in your own token, this token is invalid since I have revoked it.
          // Fetch request and return content
          .execute().returnContent();
      // Print content
      JsonParser parser = new JsonParser();
      JsonArray jsonArray = parser.parse(content.toString()).getAsJsonArray();
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
      //Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
      int i = 0;
      openDB(prop.getProperty("host"), prop.getProperty("database"),
          prop.getProperty("user"), prop.getProperty("password"));
      for (JsonElement jsonElement : jsonArray) {
        i++;
        Issue issue = gson.fromJson(jsonElement, Issue.class);
        int issue_id = issue.getId();
        int issue_number = issue.getNumber();
        int comment_number = issue.getComments();
        String title = issue.getTitle().replace("'", "''");
        //System.out.println("title: " + title);
        String state = issue.getState();
        String created_at_s = issue.getCreatedAt();
        String closed_at_s = null;
        try {
          closed_at_s = issue.getClosedAt().toString();
        } catch (Exception e) {
          closed_at_s = null;
        }
        Statement stmt;
        if (con != null) {
          stmt = con.createStatement();
          if (closed_at_s == null) {
            stmt.execute(
                "insert into issue(repo_id,issue_id,issue_number,comment_number,title,state,create_at,closed_at) values("
                    + String.valueOf(repo_id) + ","
                    + String.valueOf(issue_id) + "," + String.valueOf(issue_number) + ","
                    + String.valueOf(comment_number) + ",'" + title + "','" + state + "','"
                    + created_at_s + "'," + closed_at_s + ")");
          } else {
            stmt.execute(
                "insert into issue(repo_id,issue_id,issue_number,comment_number,title,state,create_at,closed_at) values("
                    + String.valueOf(repo_id) + "," + String.valueOf(issue_id) + ","
                    + String.valueOf(issue_number) + ","
                    + String.valueOf(comment_number) + ",'" + title + "','" + state + "','"
                    + created_at_s + "','" + closed_at_s + "')");
          }

          stmt.close();
        }

      }
      if (i < 100) {
        closeDB();
        System.out.println("Collect issue finished");
        return;
      } else {
        closeDB();
        getIssue(page + 1);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void addCommitBetweenRelease() {
    openDB(prop.getProperty("host"), prop.getProperty("database"),
        prop.getProperty("user"), prop.getProperty("password"));
    try {
      Statement stmt = con.createStatement();
      stmt.execute(
          "update release set commit_between_release =table5.commit_between_release from\n" +
              "    (select id,release_name,released_at,id-pre_id-1 as commit_between_release from (select id,release_name,released_at,coalesce(pre_id,0) as pre_id from (select id,release_name,released_at,tag,lag(id, 1)\n"
              +
              " over (\n" +
              " order by id) as pre_id from (select row_number() over () as id, release_name, released_at, tag\n"
              +
              "from (select release_name, released_at, 'release' as tag\n" +
              "      from release\n where repo_id=" +
              String.valueOf(repo_id) +
              "      union\n" +
              "      select sha as release_name, committed_at as released_at, 'commit' as tag\n" +
              "      from commit where repo_id=\n" +
              String.valueOf(repo_id) +
              "      order by released_at) as table1) as table2 where tag='release') as table3) as table4 order by id desc) as table5 where release.release_name = table5.release_name;\n");
      stmt.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println("Add commit between release finished");
    closeDB();
  }

  private static void insertRepo() {
    openDB(prop.getProperty("host"), prop.getProperty("database"),
        prop.getProperty("user"), prop.getProperty("password"));
    try {
      Statement stmt = con.createStatement();
      stmt.execute(
          "insert into repo(repo_id,repo_name) values (" + String.valueOf(repo_id) + ",'" + path
              + "')");
      stmt.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println("Insert repo finished");
    closeDB();
  }

}
