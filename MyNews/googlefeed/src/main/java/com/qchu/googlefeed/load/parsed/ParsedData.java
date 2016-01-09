package com.qchu.googlefeed.load.parsed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public class ParsedData {
  @SerializedName("feed") private ParsedFeed feed;

  public ParsedFeed getFeed() {
    return feed;
  }
}
