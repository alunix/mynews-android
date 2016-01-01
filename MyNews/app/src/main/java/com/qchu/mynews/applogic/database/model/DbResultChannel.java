package com.qchu.mynews.applogic.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@DatabaseTable(tableName = DbResultChannel.TABLE)
public class DbResultChannel {
  public static final String TABLE = "ResultChannel";

  @DatabaseField(generatedId = true)
  private long id;

  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private DbChannel channel;

  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private DbResult result;
}
