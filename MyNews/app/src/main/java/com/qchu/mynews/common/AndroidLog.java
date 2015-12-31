package com.qchu.mynews.common;

import com.qchu.common.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
@Singleton
public class AndroidLog implements Log {

  @Inject public AndroidLog(){}

  @Override
  public void d(String tag, String message) {
    android.util.Log.d(tag, message);
  }

  @Override
  public void e(String tag, String message) {
    android.util.Log.e(tag, message);
  }
}
