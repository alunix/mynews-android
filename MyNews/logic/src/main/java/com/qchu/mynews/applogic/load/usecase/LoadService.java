package com.qchu.mynews.applogic.load.usecase;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface LoadService {
  void load (String rssUrl, OnLoadListener onLoadListener);
  void load (List<String> rssUrls, OnLoadListener onLoadListener);
}
