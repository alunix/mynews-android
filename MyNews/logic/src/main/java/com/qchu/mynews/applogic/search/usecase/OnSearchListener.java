package com.qchu.mynews.applogic.search.usecase;

import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.search.entity.Result;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface OnSearchListener {
  void onStarted ();
  void onNext (String keyword, Result result);
  void onError (Throwable error);
  void onCompleted ();
}
