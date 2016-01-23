package com.qchu.feedly.load.parsed.stream;

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
}
