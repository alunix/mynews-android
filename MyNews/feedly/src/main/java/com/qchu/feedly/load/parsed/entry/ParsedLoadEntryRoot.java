package com.qchu.feedly.load.parsed.entry;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */
public class ParsedLoadEntryRoot {
  @SerializedName("continuation") private String continuation;
  @SerializedName("ids") private List<String> ids;

  public String getContinuation() {
    return continuation;
  }

  public List<String> getIds() {
    return ids;
  }
}
