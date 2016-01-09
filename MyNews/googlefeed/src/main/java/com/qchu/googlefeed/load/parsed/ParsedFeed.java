package com.qchu.googlefeed.load.parsed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public class ParsedFeed {
  @SerializedName("feedUrl") private String feedUrl;
  @SerializedName("title") private String title;
  @SerializedName("link") private String link;
  @SerializedName("author") private String author;
  @SerializedName("description") private String description;
  @SerializedName("type") private String type;
  @SerializedName("entries") private List<ParsedEntry> entries;

  public String getFeedUrl() {
    return feedUrl;
  }

  public String getTitle() {
    return title;
  }

  public String getLink() {
    return link;
  }

  public String getAuthor() {
    return author;
  }

  public String getDescription() {
    return description;
  }

  public String getType() {
    return type;
  }

  public List<ParsedEntry> getEntries() {
    return entries;
  }
}
