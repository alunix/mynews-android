package com.qchu.mynews.applogic.database.model;

import com.google.common.base.MoreObjects;
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

  @DatabaseField
  private String iconUrl;

  @DatabaseField
  private String thumbnailUrl;

  @ForeignCollectionField
  private ForeignCollection<DbResultChannel> resultChannels;

  public String getRssUrl() {
    return rssUrl;
  }

  public DbChannel setRssUrl(String rssUrl) {
    this.rssUrl = rssUrl;
    return this;
  }

  public String getLink() {
    return link;
  }

  public DbChannel setLink(String link) {
    this.link = link;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public DbChannel setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContentSnippet() {
    return contentSnippet;
  }

  public DbChannel setContentSnippet(String contentSnippet) {
    this.contentSnippet = contentSnippet;
    return this;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public DbChannel setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
    return this;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public DbChannel setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
    return this;
  }

  public ForeignCollection<DbResultChannel> getResultChannels() {
    return resultChannels;
  }

  public DbChannel setResultChannels(ForeignCollection<DbResultChannel> resultChannels) {
    this.resultChannels = resultChannels;
    return this;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("rssUrl", rssUrl)
      .add("link", link)
      .add("title", title)
      .add("contentSnippet", contentSnippet)
      .add("iconUrl", iconUrl)
      .add("thumbnailUrl", thumbnailUrl)
      .add("resultChannels", resultChannels)
      .toString();
  }
}
