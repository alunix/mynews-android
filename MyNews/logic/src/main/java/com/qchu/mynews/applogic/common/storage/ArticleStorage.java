package com.qchu.mynews.applogic.common.storage;

import com.qchu.mynews.applogic.common.Priority;
import com.qchu.mynews.applogic.common.entity.Article;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public interface ArticleStorage {
  void save(List<Article> articles, Priority priority, OnSaveListener<Article> onSaveListener);
}
