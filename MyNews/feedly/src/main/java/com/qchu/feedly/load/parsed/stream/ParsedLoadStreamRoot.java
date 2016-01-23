package com.qchu.feedly.load.parsed.stream;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */
public class ParsedLoadStreamRoot {
  @SerializedName("id") private String id;
  @SerializedName("direction") private String direction;
  @SerializedName("updated") private long updated;
  @SerializedName("title") private String title;
  @SerializedName("alternate") private List<String> alternates;
  @SerializedName("continuation") private String continuation;
  @SerializedName("items") private List<ParsedItem> items;

  public String getId() {
    return id;
  }

  public String getDirection() {
    return direction;
  }

  public long getUpdated() {
    return updated;
  }

  public String getTitle() {
    return title;
  }

  public List<String> getAlternates() {
    return alternates;
  }

  public String getContinuation() {
    return continuation;
  }

  public List<ParsedItem> getItems() {
    return items;
  }
}
