package com.qchu.mynews.applogic.recommanded.usecase;

import com.qchu.mynews.applogic.common.entity.Article;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public interface OnSynchronizeListener {
  void onStart();
  void onNext(String rssUrl, List<Article> articles);
  void onError(Throwable error);
  void onCompleted();
}
