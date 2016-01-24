package com.qchu.feedly.load.parsed.stream;

import com.google.common.base.MoreObjects;
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
  @SerializedName("alternate") private List<ParsedAlternate> alternates;
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

  public List<ParsedAlternate> getAlternates() {
    return alternates;
  }

  public String getContinuation() {
    return continuation;
  }

  public List<ParsedItem> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("id", id)
      .add("direction", direction)
      .add("updated", updated)
      .add("title", title)
      .add("alternates", alternates)
      .add("continuation", continuation)
      .add("items", items)
      .toString();
  }
}
