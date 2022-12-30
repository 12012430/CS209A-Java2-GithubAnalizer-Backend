package com.example.cs209a_final_proj.DataCollect;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Release {

  @SerializedName("url")
  private String url;
  @SerializedName("assets_url")
  private String assetsUrl;
  @SerializedName("upload_url")
  private String uploadUrl;
  @SerializedName("html_url")
  private String htmlUrl;
  @SerializedName("id")
  private Integer id;
  @SerializedName("author")
  private AuthorDTO author;
  @SerializedName("node_id")
  private String nodeId;
  @SerializedName("tag_name")
  private String tagName;
  @SerializedName("target_commitish")
  private String targetCommitish;
  @SerializedName("name")
  private String name;
  @SerializedName("draft")
  private Boolean draft;
  @SerializedName("prerelease")
  private Boolean prerelease;
  @SerializedName("created_at")
  private String createdAt;
  @SerializedName("published_at")
  private String publishedAt;
  @SerializedName("assets")
  private List<AssetsDTO> assets;
  @SerializedName("tarball_url")
  private String tarballUrl;
  @SerializedName("zipball_url")
  private String zipballUrl;
  @SerializedName("body")
  private String body;
  @SerializedName("reactions")
  private ReactionsDTO reactions;

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
  public static class ReactionsDTO {

    @SerializedName("url")
    private String url;
    @SerializedName("total_count")
    private Integer totalCount;
    @SerializedName("+1")
    private Integer _$1117;// FIXME check this code
    @SerializedName("-1")
    private Integer $1;
    @SerializedName("laugh")
    private Integer laugh;
    @SerializedName("hooray")
    private Integer hooray;
    @SerializedName("confused")
    private Integer confused;
    @SerializedName("heart")
    private Integer heart;
    @SerializedName("rocket")
    private Integer rocket;
    @SerializedName("eyes")
    private Integer eyes;
  }

  @NoArgsConstructor
  @Data
  public static class AssetsDTO {

    @SerializedName("url")
    private String url;
    @SerializedName("id")
    private Integer id;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("name")
    private String name;
    @SerializedName("label")
    private String label;
    @SerializedName("uploader")
    private UploaderDTO uploader;
    @SerializedName("content_type")
    private String contentType;
    @SerializedName("state")
    private String state;
    @SerializedName("size")
    private Integer size;
    @SerializedName("download_count")
    private Integer downloadCount;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("browser_download_url")
    private String browserDownloadUrl;

    @NoArgsConstructor
    @Data
    public static class UploaderDTO {

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
  }
}
