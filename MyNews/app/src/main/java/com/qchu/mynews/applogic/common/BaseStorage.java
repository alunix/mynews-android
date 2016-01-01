package com.qchu.mynews.applogic.common;

import com.qchu.common.Log;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public abstract class BaseStorage {
  protected final Log log;

  protected BaseStorage(Log log) {
    this.log = log;
  }
}
