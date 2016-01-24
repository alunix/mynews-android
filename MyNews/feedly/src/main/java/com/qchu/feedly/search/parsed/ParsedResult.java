package com.qchu.feedly.search.parsed;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 08/01/16.
 */
public class ParsedResult {
  @SerializedName("feedId") private String feedId;
  @SerializedName("title") private String title;
  @SerializedName("language") private String language;
  @SerializedName("velocity") private float velocity;
  @SerializedName("subscribers") private int subscribers;
  @SerializedName("lastUpdated") private long lastUpdated;
  @SerializedName("website") private String website;
  @SerializedName("score") private float score;
  @SerializedName("coverage") private int coverage;
  @SerializedName("coverageScore") private int coverageScore;
  @SerializedName("estimatedEngagement") private int estimatedEngagement;
  @SerializedName("hint") private String hint;
  @SerializedName("curated") private boolean curated;
  @SerializedName("scheme") private String scheme;
  @SerializedName("description") private String description;
  @SerializedName("contentType") private String contentType;
  @SerializedName("coverUrl") private String coverUrl;
  @SerializedName("iconUrl") private String iconUrl;
  @SerializedName("partial") private boolean partial;
  @SerializedName("twitterScreenName") private String twitterScreenName;
  @SerializedName("visualUrl") private String visualUrl;
  @SerializedName("coverColor") private String coverColor;
  @SerializedName("twitterFollowers") private int twitterFollowers;
  @SerializedName("facebookUsername") private String facebookUsername;
  @SerializedName("facebookLikes") private int facebookLikes;
  @SerializedName("art") private int art;
  @SerializedName("deliciousTags") private List<String> deliciousTags;

  public String getFeedId() {
    return feedId;
  }

  public String getLanguage() {
    return language;
  }

  public float getVelocity() {
    return velocity;
  }

  public int getSubscribers() {
    return subscribers;
  }

  public long getLastUpdated() {
    return lastUpdated;
  }

  public String getWebsite() {
    return website;
  }

  public float getScore() {
    return score;
  }

  public int getCoverage() {
    return coverage;
  }

  public int getCoverageScore() {
    return coverageScore;
  }

  public int getEstimatedEngagement() {
    return estimatedEngagement;
  }

  public String getHint() {
    return hint;
  }

  public boolean isCurated() {
    return curated;
  }

  public String getScheme() {
    return scheme;
  }

  public String getDescription() {
    return description;
  }

  public String getContentType() {
    return contentType;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public boolean isPartial() {
    return partial;
  }

  public String getTwitterScreenName() {
    return twitterScreenName;
  }

  public String getVisualUrl() {
    return visualUrl;
  }

  public String getCoverColor() {
    return coverColor;
  }

  public int getTwitterFollowers() {
    return twitterFollowers;
  }

  public String getFacebookUsername() {
    return facebookUsername;
  }

  public int getFacebookLikes() {
    return facebookLikes;
  }

  public int getArt() {
    return art;
  }

  public List<String> getDeliciousTags() {
    return deliciousTags;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("feedId", feedId)
      .add("title", title)
      .add("language", language)
      .add("velocity", velocity)
      .add("subscribers", subscribers)
      .add("lastUpdated", lastUpdated)
      .add("website", website)
      .add("score", score)
      .add("coverage", coverage)
      .add("coverageScore", coverageScore)
      .add("estimatedEngagement", estimatedEngagement)
      .add("hint", hint)
      .add("curated", curated)
      .add("scheme", scheme)
      .add("description", description)
      .add("contentType", contentType)
      .add("coverUrl", coverUrl)
      .add("iconUrl", iconUrl)
      .add("partial", partial)
      .add("twitterScreenName", twitterScreenName)
      .add("visualUrl", visualUrl)
      .add("coverColor", coverColor)
      .add("twitterFollowers", twitterFollowers)
      .add("facebookUsername", facebookUsername)
      .add("facebookLikes", facebookLikes)
      .add("art", art)
      .add("deliciousTags", deliciousTags)
      .toString();
  }
}
