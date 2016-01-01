package com.qchu.mynews.applogic.load;

import com.qchu.common.Log;
import com.qchu.mynews.applogic.common.BaseStorage;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.applogic.load.usecase.LoadStorage;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class DefaultLoadStorage extends BaseStorage implements LoadStorage {

  @Inject
  public DefaultLoadStorage(Log log) {
    super(log);
  }

  @Override
  public void save(Feed feed) {

  }

  @Override
  public Feed load(String rssUrl) {
    return null;
  }
}
