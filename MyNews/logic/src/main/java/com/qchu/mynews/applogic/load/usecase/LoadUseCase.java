package com.qchu.mynews.applogic.load.usecase;

import com.qchu.mynews.applogic.common.Priority;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface LoadUseCase {
  void load (String rssUrl, Priority priority, OnLoadListener onLoadListener);
  void load (List<String> rssUrls, Priority priority, OnLoadListener onLoadListener);
}
