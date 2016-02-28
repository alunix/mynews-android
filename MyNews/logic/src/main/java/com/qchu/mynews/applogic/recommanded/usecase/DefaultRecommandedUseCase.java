package com.qchu.mynews.applogic.recommanded.usecase;

import com.qchu.common.utils.Log;
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
  public void synchronize(String rssUrl) {

  }

  @Override
  public void synchronize(List<String> rssUrls, final OnSynchronizeListener onSynchronizeListener) {
    if(onSynchronizeListener == null) return;

    loadUseCase.load(rssUrls, new OnLoadListener() {
      @Override
      public void onStarted() {
        onSynchronizeListener.onStart();
      }

      @Override
      public void onNext(String rssUrl, Feed feed) {
        log.d(TAG, "synchronze onNext: "+ feed);
        articleStorage.save(feed.articles(), new OnSaveListener<Article>() {
          @Override
          public void onSave(List<Article> articles) {

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
