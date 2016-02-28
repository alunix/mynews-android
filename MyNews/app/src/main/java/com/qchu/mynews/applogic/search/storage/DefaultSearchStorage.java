package com.qchu.mynews.applogic.search.storage;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.qchu.common.Log;
import com.qchu.mynews.applogic.common.storage.BaseStorage;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.database.OrmliteHelper;
import com.qchu.mynews.applogic.database.model.DbChannel;
import com.qchu.mynews.applogic.database.model.DbResult;
import com.qchu.mynews.applogic.database.model.DbResultChannel;
import com.qchu.mynews.applogic.search.entity.Result;
import com.qchu.mynews.applogic.search.storage.SearchStorage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import autovalue.shaded.com.google.common.common.collect.Lists;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class DefaultSearchStorage extends BaseStorage implements SearchStorage {

  private final static String TAG = "DefaultSearchStorage";

  @Inject
  public DefaultSearchStorage(OrmliteHelper ormliteHelper, Log log){
    super(ormliteHelper, log);
  }

  @Override
  public void save(Result result) {
    log.d(TAG, "save: result " + result);
    DbResult dbResult = null;
    try {
      dbResult = ormliteHelper.resultDao().queryForId(result.keyword());
    } catch (SQLException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    if(dbResult == null) {
      dbResult = new DbResult();
      dbResult.setKeyword(result.keyword());
    }

    dbResult.setSearchedDate(result.searchedDate());

    //Delete all the result channel that haven't any relation with result
    Collection<String> channelIds = Collections2.transform(result.channels(),
      new Function<Channel, String>() {
        @Nullable @Override public String apply(@Nullable Channel input) {
          return input.rssUrl();
        }
      });

    try {
      ormliteHelper.resultChannelDao().deleteBuilder()
        .where().eq(DbResultChannel.COLUMN_RESULT, result.keyword())
        .and()
        .not().in(DbResultChannel.COLUMN_CHANNEL,channelIds).prepare();
    } catch (SQLException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    //save channels and resultChannels
    for(Channel channel: result.channels()) {
      saveDbChannelFrom(channel);
      saveDbResultChannel(result.keyword(), channel.rssUrl());
    }

    try {
      ormliteHelper.resultDao().createOrUpdate(dbResult);
    } catch (SQLException e) {
      log.e(TAG, e.getLocalizedMessage());
    }
  }

  @Override
  public Result load(String keyword) {
    log.d(TAG, "load: keyword " + keyword);
    try {
      return resultFrom(ormliteHelper.resultDao().queryForId(keyword));
    } catch (SQLException e) {
      log.e(TAG, e.getLocalizedMessage());
    }
    return null;
  }

  @Override
  public List<Result> resultsBetween(Date from, Date to) {
    try {
      List<DbResult> dbResults = ormliteHelper.resultDao().queryBuilder()
        .where().ge(DbResult.COLUMN_SEARCHED_DATE, from)
        .and().le(DbResult.COLUMN_SEARCHED_DATE, to).query();

      return Lists.newArrayList(Collections2.transform(dbResults, new Function<DbResult, Result>() {
        @Nullable
        @Override
        public Result apply(@Nullable DbResult input) {
          return resultFrom(input);
        }
      }));

    } catch (SQLException e) {
      log.e(TAG, e.getLocalizedMessage());
    }
    return null;
  }

  private Result resultFrom (DbResult dbResult){
    if (dbResult == null) return null;

    List<Channel> channels = Lists.newArrayList(Collections2.transform(dbResult.getResultChannels(),
      new Function<DbResultChannel, Channel>() {
      @Nullable @Override public Channel apply(DbResultChannel input) {
        if(input == null || input.getChannel() == null) return null;

        try {
          DbChannel dbChannel = ormliteHelper.channelDao()
            .queryForId(input.getChannel().getRssUrl());
          return channelFrom (dbChannel);
        } catch (SQLException e) {
          log.e(TAG, e.getLocalizedMessage());
        }

        return null;
      }
    }));

    return Result.builder()
      .keyword(dbResult.getKeyword())
      .searchedDate(dbResult.getSearchedDate())
      .channels(channels)
      .build();
  }

  private Channel channelFrom (DbChannel dbChannel){
    if (dbChannel == null) return null;

    return Channel.builder()
      .rssUrl(dbChannel.getRssUrl())
      .link(dbChannel.getLink())
      .title(dbChannel.getTitle())
      .contentSnippet(dbChannel.getContentSnippet())
      .build();
  }

  private DbChannel saveDbChannelFrom (Channel channel){
    DbChannel dbChannel = null;
    try {
      dbChannel = ormliteHelper.channelDao().queryForId(channel.rssUrl());
    } catch (SQLException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    if(dbChannel == null) {
      dbChannel = new DbChannel();
      dbChannel.setRssUrl(channel.rssUrl());
    }

    dbChannel.setTitle(channel.title());
    dbChannel.setLink(channel.link());
    dbChannel.setContentSnippet(channel.contentSnippet());

    try {
      ormliteHelper.channelDao().createOrUpdate(dbChannel);
    } catch (SQLException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    return dbChannel;
  }

  private DbResultChannel saveDbResultChannel (String keyword, String rssUrl) {
    DbResultChannel dbResultChannel = null;

    try {
      dbResultChannel = ormliteHelper.resultChannelDao().queryBuilder()
        .where().eq(DbResultChannel.COLUMN_RESULT, keyword)
        .and().eq(DbResultChannel.COLUMN_CHANNEL, rssUrl)
        .queryForFirst();

    } catch (SQLException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    if(dbResultChannel == null) {
      dbResultChannel = new DbResultChannel();

      DbResult dbResult = new DbResult();
      dbResult.setKeyword(keyword);

      DbChannel dbChannel = new DbChannel();
      dbChannel.setRssUrl(rssUrl);

      dbResultChannel.setResult(dbResult);
      dbResultChannel.setChannel(dbChannel);
    }

    try {
      ormliteHelper.resultChannelDao().createOrUpdate(dbResultChannel);
    } catch (SQLException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    return dbResultChannel;
  }
}
