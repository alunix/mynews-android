package com.qchu.googlefeed.search.parsed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public class ParsedData {
  @SerializedName("query") private String query;
  @SerializedName("entries") private List<ParsedEntry> entries;

  public String getQuery() {
    return query;
  }

  public List<ParsedEntry> getEntries() {
    return entries;
  }
}
