package com.qchu.googlefeed.search.parsed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public class ParsedSearchRoot {
  @SerializedName("responseData") private ParsedData data;
  @SerializedName("responseStatus") private int status;

  public ParsedData getData() {
    return data;
  }

  public void setData(ParsedData data) {
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
