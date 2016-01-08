package com.qchu.rss;

import com.qchu.rss.entity.Channel;

/**
 * Created by Quoc Dung Chu on 02/01/16.
 */
public interface RssApi {
  void load(String rssUrl, OnLoadListener onLoadListener);

  interface OnLoadListener {
    void onStarted(String rssUrl);
    void onNext (String rssUrl, Channel channel);
    void onError (String rssUrl, Throwable error);
    void onCompleted (String rssUrl);
  }
}
