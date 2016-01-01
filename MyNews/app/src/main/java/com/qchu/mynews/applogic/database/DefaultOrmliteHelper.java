package com.qchu.mynews.applogic.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.qchu.common.Log;
import com.qchu.mynews.applogic.database.model.DbArticle;
import com.qchu.mynews.applogic.database.model.DbChannel;
import com.qchu.mynews.applogic.database.model.DbFeed;
import com.qchu.mynews.applogic.database.model.DbResult;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class DefaultOrmliteHelper extends OrmLiteSqliteOpenHelper implements OrmliteHelper {

  public final static String DB_NAME = "db_ormlite_my_news";
  public final static int DB_VERSION = 1;
  private final Log log;

  @Inject
  public DefaultOrmliteHelper(Context context, Log log) {
    super(context, DB_NAME, null, DB_VERSION);
    this.log = log;
  }

  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try {
      log.d(DefaultOrmliteHelper.class.getName(), "onCreate");
      TableUtils.createTable(connectionSource, DbArticle.class);
      TableUtils.createTable(connectionSource, DbChannel.class);
      TableUtils.createTable(connectionSource, DbFeed.class);
      TableUtils.createTable(connectionSource, DbResult.class);

    } catch (SQLException e) {
      log.e(DefaultOrmliteHelper.class.getName(), "Can't create database " + e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    try {
      log.d(DefaultOrmliteHelper.class.getName(), "onUpgrade");
      TableUtils.dropTable(connectionSource, DbArticle.class, true);
      TableUtils.dropTable(connectionSource, DbChannel.class, true);
      TableUtils.dropTable(connectionSource, DbFeed.class, true);
      TableUtils.dropTable(connectionSource, DbResult.class, true);

      // after we drop the old databases, we create the new ones
      onCreate(database, connectionSource);
    } catch (SQLException e) {
      log.e(DefaultOrmliteHelper.class.getName(), "Can't drop databases" + e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
  }
}
