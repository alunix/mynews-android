package com.qchu.mynews.applogic.database.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@DatabaseTable(tableName = DbArticle.TABLE)
public class DbArticle {
  public static final String TABLE = "Article";
  public static final String COLUMN_PUBLISHED_DATE = "published_date";

  @DatabaseField(id = true, index = true)
  private String link;

  @DatabaseField
  private String title;

  @DatabaseField
  private String contentSnippet;

  @DatabaseField
  private String content;

  @DatabaseField
  private String author;

  @DatabaseField(dataType = DataType.DATE, columnName = COLUMN_PUBLISHED_DATE)
  private Date publishedDate;

  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private DbChannel channel;

  @ForeignCollectionField
  private ForeignCollection<DbFeedArticle> feedArticles;

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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(Date publishedDate) {
    this.publishedDate = publishedDate;
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
}
