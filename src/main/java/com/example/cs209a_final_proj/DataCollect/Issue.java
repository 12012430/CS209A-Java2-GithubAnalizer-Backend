package com.example.cs209a_final_proj.DataCollect;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Issue {

    @SerializedName("url")
    private String url;
    @SerializedName("repository_url")
    private String repositoryUrl;
    @SerializedName("labels_url")
    private String labelsUrl;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("id")
    private Integer id;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("number")
    private Integer number;
    @SerializedName("title")
    private String title;
    @SerializedName("user")
    private UserDTO user;
    @SerializedName("labels")
    private List<?> labels;
    @SerializedName("state")
    private String state;
    @SerializedName("locked")
    private Boolean locked;
    @SerializedName("assignee")
    private Object assignee;
    @SerializedName("assignees")
    private List<?> assignees;
    @SerializedName("milestone")
    private Object milestone;
    @SerializedName("comments")
    private Integer comments;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("closed_at")
    private Object closedAt;
    @SerializedName("author_association")
    private String authorAssociation;
    @SerializedName("active_lock_reason")
    private Object activeLockReason;
    @SerializedName("draft")
    private Boolean draft;
    @SerializedName("pull_request")
    private PullRequestDTO pullRequest;
    @SerializedName("body")
    private String body;
    @SerializedName("reactions")
    private ReactionsDTO reactions;
    @SerializedName("timeline_url")
    private String timelineUrl;
    @SerializedName("performed_via_github_app")
    private Object performedViaGithubApp;
    @SerializedName("state_reason")
    private Object stateReason;

    @NoArgsConstructor
    @Data
    public static class UserDTO {

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
    public static class PullRequestDTO {

        @SerializedName("url")
        private String url;
        @SerializedName("html_url")
        private String htmlUrl;
        @SerializedName("diff_url")
        private String diffUrl;
        @SerializedName("patch_url")
        private String patchUrl;
        @SerializedName("merged_at")
        private Object mergedAt;
    }

    @NoArgsConstructor
    @Data
    public static class ReactionsDTO {

        @SerializedName("url")
        private String url;
        @SerializedName("total_count")
        private Integer totalCount;
        @SerializedName("+1")
        private Integer _$1172;// FIXME check this code
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
}

