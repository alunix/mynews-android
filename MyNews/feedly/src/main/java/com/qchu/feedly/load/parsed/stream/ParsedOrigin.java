package com.qchu.feedly.load.parsed.stream;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */

public class ParsedOrigin {
  @SerializedName("streamId") private String streamId;
  @SerializedName("title") private String title;
  @SerializedName("htmlUrl") private String htmlUrl;

  public String getStreamId() {
    return streamId;
  }

  public String getTitle() {
    return title;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }
}
