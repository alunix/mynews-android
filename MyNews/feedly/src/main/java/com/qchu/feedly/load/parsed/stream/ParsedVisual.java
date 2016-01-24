package com.qchu.feedly.load.parsed.stream;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Dung Chu on 24/01/16.
 */
public class ParsedVisual {
  @SerializedName("url") private String url;
  @SerializedName("width") private int width;
  @SerializedName("height") private int height;
  @SerializedName("processor") private String processor;
  @SerializedName("contentType") private String contentType;

  public String getUrl() {
    return url;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getProcessor() {
    return processor;
  }

  public String getContentType() {
    return contentType;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("url", url)
      .add("width", width)
      .add("height", height)
      .add("processor", processor)
      .add("contentType", contentType)
      .toString();
  }
}
