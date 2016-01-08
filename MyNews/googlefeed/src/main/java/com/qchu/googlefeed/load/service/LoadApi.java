package com.qchu.googlefeed.load.service;

import com.qchu.googlefeed.load.entity.Feed;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public interface LoadApi {
  void load(String rssUrl, OnLoadListener onLoadListener);

  interface OnLoadListener {
    void onStarted(String rssUrl);
    void onNext (String rssUrl, Feed feed);
    void onError (String rssUrl, Throwable error);
    void onCompleted (String rssUrl);
  }
}
