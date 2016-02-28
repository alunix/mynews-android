package com.qchu.mynews.applogic.database.model;

import com.google.common.base.MoreObjects;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@DatabaseTable(tableName = DbFeedArticle.TABLE)
public class DbFeedArticle {
  public static final String TABLE = "FeedArticle";

  @DatabaseField(generatedId = true)
  private long id;

  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private DbArticle article;

  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private DbFeed feed;

  public DbArticle getArticle() {
    return article;
  }

  public void setArticle(DbArticle article) {
    this.article = article;
  }

  public DbFeed getFeed() {
    return feed;
  }

  public void setFeed(DbFeed feed) {
    this.feed = feed;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("id", id)
      .add("article", article)
      .add("feed", feed)
      .toString();
  }
}
