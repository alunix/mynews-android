package com.qchu.feedly.search.parsed;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 08/01/16.
 */
public class ParsedSearchRoot {
  @SerializedName("hint") private String hint;
  @SerializedName("queryType") private String queryType;
  @SerializedName("scheme") private String scheme;
  @SerializedName("related") private List<String> related;
  @SerializedName("results") private List<ParsedResult> results;

  public String getHint() {
    return hint;
  }

  public String getQueryType() {
    return queryType;
  }

  public List<ParsedResult> getResults() {
    return results;
  }

  public String getScheme() {
    return scheme;
  }

  public List<String> getRelated() {
    return related;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("hint", hint)
      .add("queryType", queryType)
      .add("scheme", scheme)
      .add("related", related)
      .add("results", results)
      .toString();
  }
}
