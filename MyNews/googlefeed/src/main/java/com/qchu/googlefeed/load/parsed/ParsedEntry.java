package com.qchu.googlefeed.load.parsed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public class ParsedEntry {
  @SerializedName("title") private String title;
  @SerializedName("link") private String link;
  @SerializedName("author") private String author;
  @SerializedName("contentSnippet") private String contentSnippet;
  @SerializedName("content") private String content;
  @SerializedName("publishedDate") private String publishedDate;
  @SerializedName("categories") private List<String> categories;

  public String getTitle() {
    return title;
  }

  public String getLink() {
    return link;
  }

  public String getAuthor() {
    return author;
  }

  public String getContentSnippet() {
    return contentSnippet;
  }

  public String getContent() {
    return content;
  }

  public String getPublishedDate() {
    return publishedDate;
  }

  public List<String> getCategories() {
    return categories;
  }
}
