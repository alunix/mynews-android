package com.qchu.mynews.applogic.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.qchu.common.utils.Log;
import com.qchu.mynews.applogic.database.model.DbArticle;
import com.qchu.mynews.applogic.database.model.DbChannel;
import com.qchu.mynews.applogic.database.model.DbFeed;
import com.qchu.mynews.applogic.database.model.DbFeedArticle;
import com.qchu.mynews.applogic.database.model.DbResult;
import com.qchu.mynews.applogic.database.model.DbResultChannel;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class DefaultOrmliteHelper extends OrmLiteSqliteOpenHelper implements OrmliteHelper {

  private final static String TAG = "DefaultOrmliteHelper";
  public final static String DB_NAME = "db_ormlite_my_news";
  public final static int DB_VERSION = 1;
  private final Log log;

  private Dao<DbArticle, String> articleDao;
  private Dao<DbChannel, String> channelDao;
  private Dao<DbFeed, String> feedDao;
  private Dao<DbFeedArticle, Long> feedArticleDao;
  private Dao<DbResult, String> resultDao;
  private Dao<DbResultChannel, Long> resultChannelDao;

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
      TableUtils.createTable(connectionSource, DbFeedArticle.class);
      TableUtils.createTable(connectionSource, DbResult.class);
      TableUtils.createTable(connectionSource, DbResultChannel.class);


    } catch (SQLException e) {
      log.e(TAG, "Can't create database " + e.getLocalizedMessage());
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
      TableUtils.dropTable(connectionSource, DbFeedArticle.class, true);
      TableUtils.dropTable(connectionSource, DbResult.class, true);
      TableUtils.dropTable(connectionSource, DbResultChannel.class, true);


      // after we drop the old databases, we create the new ones
      onCreate(database, connectionSource);
    } catch (SQLException e) {
      log.e(TAG, "Can't drop databases" + e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
  }

  @Override
  public Dao<DbArticle, String> articleDao() {
    if(articleDao == null){
      try {
        articleDao = getDao(DbArticle.class);
      } catch (SQLException e) {
        log.e(TAG, e.getLocalizedMessage());
      }
    }
    return articleDao;
  }

  @Override
  public Dao<DbChannel, String> channelDao() {
    if(channelDao == null){
      try {
        channelDao = getDao(DbChannel.class);
      } catch (SQLException e) {
        log.e(TAG, e.getLocalizedMessage());
      }
    }
    return channelDao;
  }

  @Override
  public Dao<DbFeed, String> feedDao() {
    if(feedDao == null){
      try {
        feedDao = getDao(DbFeed.class);
      } catch (SQLException e) {
        log.e(TAG, e.getLocalizedMessage());
      }
    }
    return feedDao;
  }

  @Override
  public Dao<DbFeedArticle, Long> feedArticleDao() {
    if(feedArticleDao == null){
      try {
        feedArticleDao = getDao(DbFeedArticle.class);
      } catch (SQLException e) {
        log.e(TAG, e.getLocalizedMessage());
      }
    }
    return feedArticleDao;
  }

  @Override
  public Dao<DbResult, String> resultDao() {
    if(resultDao == null){
      try {
        resultDao = getDao(DbResult.class);
      } catch (SQLException e) {
        log.e(TAG, e.getLocalizedMessage());
      }
    }
    return resultDao;
  }

  @Override
  public Dao<DbResultChannel, Long> resultChannelDao() {
    if(resultChannelDao == null){
      try {
        resultChannelDao = getDao(DbResultChannel.class);
      } catch (SQLException e) {
        log.e(TAG, e.getLocalizedMessage());
      }
    }
    return resultChannelDao;
  }
}
