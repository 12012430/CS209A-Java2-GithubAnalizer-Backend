package com.example.cs209a_final_proj.DataCollect;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Commit {

  @SerializedName("sha")
  private String sha;
  @SerializedName("node_id")
  private String nodeId;
  @SerializedName("commit")
  private CommitDTO commit;
  @SerializedName("url")
  private String url;
  @SerializedName("html_url")
  private String htmlUrl;
  @SerializedName("comments_url")
  private String commentsUrl;
  @SerializedName("author")
  private AuthorDTO author;
  @SerializedName("committer")
  private CommitterDTO committer;
  @SerializedName("parents")
  private List<ParentsDTO> parents;

  @NoArgsConstructor
  @Data
  public static class CommitDTO {

    @SerializedName("author")
    private AuthorDTO author;
    @SerializedName("committer")
    private CommitterDTO committer;
    @SerializedName("message")
    private String message;
    @SerializedName("tree")
    private TreeDTO tree;
    @SerializedName("url")
    private String url;
    @SerializedName("comment_count")
    private Integer commentCount;
    @SerializedName("verification")
    private VerificationDTO verification;

    @NoArgsConstructor
    @Data
    public static class AuthorDTO {

      @SerializedName("name")
      private String name;
      @SerializedName("email")
      private String email;
      @SerializedName("date")
      private String date;
    }

    @NoArgsConstructor
    @Data
    public static class CommitterDTO {

      @SerializedName("name")
      private String name;
      @SerializedName("email")
      private String email;
      @SerializedName("date")
      private String date;
    }

    @NoArgsConstructor
    @Data
    public static class TreeDTO {

      @SerializedName("sha")
      private String sha;
      @SerializedName("url")
      private String url;
    }

    @NoArgsConstructor
    @Data
    public static class VerificationDTO {

      @SerializedName("verified")
      private Boolean verified;
      @SerializedName("reason")
      private String reason;
      @SerializedName("signature")
      private String signature;
      @SerializedName("payload")
      private String payload;
    }
  }

  @NoArgsConstructor
  @Data
  public static class AuthorDTO {

    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private Integer id;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("url")
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("followers_url")
    private String followersUrl;
    @SerializedName("following_url")
    private String followingUrl;
    @SerializedName("gists_url")
    private String gistsUrl;
    @SerializedName("starred_url")
    private String starredUrl;
    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;
    @SerializedName("organizations_url")
    private String organizationsUrl;
    @SerializedName("repos_url")
    private String reposUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("received_events_url")
    private String receivedEventsUrl;
    @SerializedName("type")
    private String type;
    @SerializedName("site_admin")
    private Boolean siteAdmin;
  }

  @NoArgsConstructor
  @Data
  public static class CommitterDTO {

    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private Integer id;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("url")
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("followers_url")
    private String followersUrl;
    @SerializedName("following_url")
    private String followingUrl;
    @SerializedName("gists_url")
    private String gistsUrl;
    @SerializedName("starred_url")
    private String starredUrl;
    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;
    @SerializedName("organizations_url")
    private String organizationsUrl;
    @SerializedName("repos_url")
    private String reposUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("received_events_url")
    private String receivedEventsUrl;
    @SerializedName("type")
    private String type;
    @SerializedName("site_admin")
    private Boolean siteAdmin;
  }

  @NoArgsConstructor
  @Data
  public static class ParentsDTO {

    @SerializedName("sha")
    private String sha;
    @SerializedName("url")
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;
  }
}
