package com.qchu.feedly.load.parsed.stream;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */
public class ParsedAlternate {
  @SerializedName("href") private String href;
  @SerializedName("type") private String type;

  public String getHref() {
    return href;
  }

  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("href", href)
      .add("type", type)
      .toString();
  }
}
