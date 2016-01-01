package com.qchu.mynews.applogic.database;

import com.j256.ormlite.dao.Dao;
import com.qchu.mynews.applogic.database.model.DbArticle;
import com.qchu.mynews.applogic.database.model.DbChannel;
import com.qchu.mynews.applogic.database.model.DbFeed;
import com.qchu.mynews.applogic.database.model.DbFeedArticle;
import com.qchu.mynews.applogic.database.model.DbResult;
import com.qchu.mynews.applogic.database.model.DbResultChannel;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface OrmliteHelper {
  Dao<DbArticle, String> articleDao();
  Dao<DbChannel, String> channelDao();
  Dao<DbFeed, String> feedDao();
  Dao<DbFeedArticle, Long> feedArticleDao();
  Dao<DbResult, String> resultDao();
  Dao<DbResultChannel, Long> resultChannelDao();
}
