package com.qchu.mynews.applogic.database.model;

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
}
