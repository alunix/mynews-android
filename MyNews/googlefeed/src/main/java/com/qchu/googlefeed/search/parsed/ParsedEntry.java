package com.qchu.googlefeed.search.parsed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public class ParsedEntry {
  @SerializedName("url") private String url;
  @SerializedName("title") private String title;
  @SerializedName("contentSnippet") private String content;
  @SerializedName("link") private String link;

  public String getUrl() {
    return url;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getLink() {
    return link;
  }
}
