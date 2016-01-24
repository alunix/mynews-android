package com.qchu.feedly.load.parsed.stream;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */
public class ParsedSummary {
  @SerializedName("content") private String content;
  @SerializedName("direction") private String direction;

  public String getContent() {
    return content;
  }

  public String getDirection() {
    return direction;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("content", content)
      .add("direction", direction)
      .toString();
  }
}
