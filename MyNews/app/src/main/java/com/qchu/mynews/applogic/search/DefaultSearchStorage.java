package com.qchu.mynews.applogic.search;

import com.qchu.common.Log;
import com.qchu.mynews.applogic.common.BaseStorage;
import com.qchu.mynews.applogic.search.entity.Result;
import com.qchu.mynews.applogic.search.usecase.SearchStorage;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class DefaultSearchStorage extends BaseStorage implements SearchStorage {

  private final static String TAG = "DefaultSearchStorage";

  @Inject
  public DefaultSearchStorage(Log log){
    super(log);
  }

  @Override
  public void save(Result result) {
    log.d(TAG, "save: result "+ result);
  }

  @Override
  public Result load(String keyword) {
    log.d(TAG, "load: keyword "+ keyword);
    return null;
  }
}
