package com.qchu.mynews.applogic.database.model;

import com.google.common.base.MoreObjects;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@DatabaseTable(tableName = DbResultChannel.TABLE)
public class DbResultChannel {
  public static final String TABLE = "ResultChannel";
  public static final String COLUMN_CHANNEL = "channel_id";
  public static final String COLUMN_RESULT = "result_id";

  @DatabaseField(generatedId = true)
  private long id;

  @DatabaseField(index = true, foreign = true, columnName = COLUMN_CHANNEL)
  private DbChannel channel;

  @DatabaseField(index = true, foreign = true, columnName = COLUMN_RESULT)
  private DbResult result;

  public DbChannel getChannel() {
    return channel;
  }

  public void setChannel(DbChannel channel) {
    this.channel = channel;
  }

  public DbResult getResult() {
    return result;
  }

  public void setResult(DbResult result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("id", id)
      .add("channel", channel)
      .add("result", result)
      .toString();
  }
}
