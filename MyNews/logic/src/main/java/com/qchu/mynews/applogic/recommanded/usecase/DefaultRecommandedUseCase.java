package com.qchu.mynews.applogic.recommanded.usecase;

import com.qchu.common.utils.ListUtils;
import com.qchu.common.utils.Log;
import com.qchu.mynews.applogic.common.Priority;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.common.storage.ArticleStorage;
import com.qchu.mynews.applogic.common.storage.OnSaveListener;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.applogic.load.usecase.LoadUseCase;
import com.qchu.mynews.applogic.load.usecase.OnLoadListener;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
@Singleton
public class DefaultRecommandedUseCase implements RecommandedUseCase {

  private static final String TAG = "DefaultRecommandedUseCase";
  private final LoadUseCase loadUseCase;
  private final ArticleStorage articleStorage;
  private final Log log;

  @Inject
  public DefaultRecommandedUseCase(
    LoadUseCase loadUseCase,
    ArticleStorage articleStorage, Log log) {

    this.loadUseCase = loadUseCase;
    this.articleStorage = articleStorage;
    this.log = log;
  }

  @Override
  public void synchronize(
    List<String> rssUrls,
    final Priority priority,
    final OnSynchronizeListener onSynchronizeListener) {

    if(ListUtils.isNullOrEmpty(rssUrls) || onSynchronizeListener == null) return;

    loadUseCase.load(rssUrls, priority, new OnLoadListener() {
      @Override
      public void onStarted() {
        onSynchronizeListener.onStart();
      }

      @Override
      public void onNext(final String rssUrl, Feed feed) {
        log.d(TAG, "synchronize onNext: "+ feed);
        articleStorage.save(feed.articles(), priority, new OnSaveListener<Article>() {
          @Override
          public void onSave(List<Article> articles) {
            onSynchronizeListener.onNext(rssUrl, articles);
          }
        });
      }

      @Override
      public void onError(Throwable error) {

      }

      @Override
      public void onCompleted() {

      }
    });
  }
}
