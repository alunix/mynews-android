package com.qchu.mynews.applogic.load.usecase;

import com.qchu.mynews.applogic.load.entity.Feed;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface OnLoadListener {
  void onStarted ();
  void onNext (String rssUrl, Feed feed);
  void onError (Throwable error);
  void onCompleted ();
}
