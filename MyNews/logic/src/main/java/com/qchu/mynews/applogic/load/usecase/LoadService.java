package com.qchu.mynews.applogic.load.usecase;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface LoadService {
  void load (String rssUrl, OnLoadListener onLoadListener);
}
