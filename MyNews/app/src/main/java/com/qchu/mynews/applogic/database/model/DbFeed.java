package com.qchu.mynews.applogic.database.model;

import com.google.common.base.MoreObjects;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@DatabaseTable(tableName = DbFeed.TABLE)
public class DbFeed {
  public static final String TABLE = "Feed";
  private static final String COLUMN_FEED_AT_DATE = "feed_at_date";

  @DatabaseField(id = true, index = true)
  private String rssUrl;

  @DatabaseField(dataType = DataType.DATE, columnName = COLUMN_FEED_AT_DATE)
  private Date feedAtDate;

  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private DbChannel channel;

  @ForeignCollectionField
  private ForeignCollection<DbFeedArticle> feedArticles;

  public String getRssUrl() {
    return rssUrl;
  }

  public void setRssUrl(String rssUrl) {
    this.rssUrl = rssUrl;
  }

  public Date getFeedAtDate() {
    return feedAtDate;
  }

  public void setFeedAtDate(Date feedAtDate) {
    this.feedAtDate = feedAtDate;
  }

  public DbChannel getChannel() {
    return channel;
  }

  public void setChannel(DbChannel channel) {
    this.channel = channel;
  }

  public ForeignCollection<DbFeedArticle> getFeedArticles() {
    return feedArticles;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("rssUrl", rssUrl)
      .add("feedAtDate", feedAtDate)
      .add("channel", channel)
      .add("feedArticles", feedArticles)
      .toString();
  }
}
