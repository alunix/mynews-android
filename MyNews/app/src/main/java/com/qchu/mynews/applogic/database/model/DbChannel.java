package com.qchu.mynews.applogic.database.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@DatabaseTable(tableName = DbChannel.TABLE)
public class DbChannel {
  public static final String TABLE = "Channel";

  @DatabaseField(id = true, index = true)
  private String rssUrl;

  @DatabaseField
  private String link;

  @DatabaseField
  private String title;

  @DatabaseField
  private String contentSnippet;

  @ForeignCollectionField
  private ForeignCollection<DbResultChannel> resultChannels;

  public String getRssUrl() {
    return rssUrl;
  }

  public void setRssUrl(String rssUrl) {
    this.rssUrl = rssUrl;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContentSnippet() {
    return contentSnippet;
  }

  public void setContentSnippet(String contentSnippet) {
    this.contentSnippet = contentSnippet;
  }

  public ForeignCollection<DbResultChannel> getResultChannels() {
    return resultChannels;
  }
}
