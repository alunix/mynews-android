package com.qchu.feedly.load.parsed.stream;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */
public class ParsedItem {
  @SerializedName("id") private String id;
  @SerializedName("keywords") private List<String> keywords;
  @SerializedName("originId") private String originId;
  @SerializedName("fingerprint") private String fingerprint;
  @SerializedName("content") private ParsedContent content;
  @SerializedName("title") private String title;
  @SerializedName("published") private Date published;
  @SerializedName("crawled") private long crawled;
  @SerializedName("summary") private ParsedSummary summary;
  @SerializedName("alternate") private List<ParsedAlternate> alternates;
  @SerializedName("author") private String author;
  @SerializedName("origin") private ParsedOrigin origin;
  @SerializedName("visual") private ParsedVisual visual;
  @SerializedName("unread") private boolean unread;

  public String getId() {
    return id;
  }

  public List<String> getKeywords() {
    return keywords;
  }

  public String getOriginId() {
    return originId;
  }

  public String getFingerprint() {
    return fingerprint;
  }

  public ParsedContent getContent() {
    return content;
  }

  public String getTitle() {
    return title;
  }

  public Date getPublished() {
    return published;
  }

  public long getCrawled() {
    return crawled;
  }

  public ParsedSummary getSummary() {
    return summary;
  }

  public List<ParsedAlternate> getAlternates() {
    return alternates;
  }

  public String getAuthor() {
    return author;
  }

  public ParsedOrigin getOrigin() {
    return origin;
  }

  public ParsedVisual getVisual() {
    return visual;
  }

  public boolean isUnread() {
    return unread;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("id", id)
      .add("keywords", keywords)
      .add("originId", originId)
      .add("fingerprint", fingerprint)
      .add("content", content)
      .add("title", title)
      .add("published", published)
      .add("crawled", crawled)
      .add("summary", summary)
      .add("alternates", alternates)
      .add("author", author)
      .add("origin", origin)
      .add("visual", visual)
      .add("unread", unread)
      .toString();
  }
}
