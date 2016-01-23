package com.qchu.googlefeed.load.parsed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public class ParsedLoadRoot {
  @SerializedName("responseData") private ParsedData data;
  @SerializedName("responseStatus") private int status;

  public ParsedData getData() {
    return data;
  }

  public int getStatus() {
    return status;
  }
}