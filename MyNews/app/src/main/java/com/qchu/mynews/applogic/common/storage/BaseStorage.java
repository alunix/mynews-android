package com.qchu.mynews.applogic.common.storage;

import com.qchu.common.utils.Log;
import com.qchu.mynews.applogic.database.OrmliteHelper;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public abstract class BaseStorage {

  protected final OrmliteHelper ormliteHelper;
  protected final Log log;

  protected BaseStorage(OrmliteHelper ormliteHelper, Log log) {
    this.ormliteHelper = ormliteHelper;
    this.log = log;
  }
}
