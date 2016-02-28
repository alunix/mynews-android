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
@DatabaseTable(tableName = DbResult.TABLE)
public class DbResult {
  public static final String TABLE = "Result";
  public static final String COLUMN_SEARCHED_DATE = "searched_date";

  @DatabaseField(id = true, index = true)
  private String keyword;

  @DatabaseField(dataType = DataType.DATE, columnName = COLUMN_SEARCHED_DATE)
  private Date searchedDate;

  @ForeignCollectionField
  private ForeignCollection<DbResultChannel> resultChannels;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Date getSearchedDate() {
    return searchedDate;
  }

  public void setSearchedDate(Date searchedDate) {
    this.searchedDate = searchedDate;
  }

  public ForeignCollection<DbResultChannel> getResultChannels() {
    return resultChannels;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("keyword", keyword)
      .add("searchedDate", searchedDate)
      .add("resultChannels", resultChannels)
      .toString();
  }
}
