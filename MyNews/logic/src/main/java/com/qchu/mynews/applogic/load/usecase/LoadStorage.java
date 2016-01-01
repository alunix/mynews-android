package com.qchu.mynews.applogic.load.usecase;

import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.load.entity.Feed;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface LoadStorage {
  void save(Feed feed);
  Feed load(String rssUrl);
}
