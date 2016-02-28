package com.qchu.mynews.common;

import android.app.Application;

import com.qchu.mynews.applogic.common.Priority;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.recommanded.usecase.OnSynchronizeListener;
import com.qchu.mynews.common.dagger.AppComponent;
import com.qchu.mynews.common.dagger.DaggerAppComponent;
import com.qchu.mynews.common.dagger.module.AppModule;

import java.util.List;

import autovalue.shaded.com.google.common.common.collect.Lists;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public class MNApplication extends Application {

  private static final String TAG = "MNApplication";
  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    appComponent = DaggerAppComponent.builder()
      .appModule(new AppModule(this))
      .build();

    appComponent().recommandedUseCase().synchronize(
      Lists.newArrayList(
        "http://thethaovanhoa.vn/trang-chu.rss",
        "http://thethaovanhoa.vn/anh.rss",
        "http://thethaovanhoa.vn/tay-ban-nha.rss",
        "http://thethaovanhoa.vn/duc.rss",
        "http://thethaovanhoa.vn/italy.rss",
        "http://thethaovanhoa.vn/champions-league.rss"
      ),
      Priority.WHENEVER,
      new OnSynchronizeListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onNext(String rssUrl, List<Article> articles) {

        }

        @Override
        public void onError(Throwable error) {

        }

        @Override
        public void onCompleted() {

        }
      });

    appComponent().recommandedUseCase().synchronize(
      Lists.newArrayList(
        "http://thethaovanhoa.vn/dien-dan-van-hoa.rss"
      ),
      Priority.IMMEDIATE,
      new OnSynchronizeListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onNext(String rssUrl, List<Article> articles) {

        }

        @Override
        public void onError(Throwable error) {

        }

        @Override
        public void onCompleted() {

        }
      });

    /*appComponent.searchUseCase().search("thethaovanhoa", new OnSearchListener() {
      @Override
      public void onStarted() {
        List<Result> results = appComponent.searchUseCase().resultsUntilNow(1);
        appComponent.log().d(TAG, "onStarted  " + results.size());
      }

      @Override
      public void onNext(String keyword, Result result) {

      }

      @Override
      public void onError(Throwable error) {

      }

      @Override
      public void onCompleted() {
        List<Result> results = appComponent.searchUseCase().resultsUntilNow(1);
        appComponent.log().d(TAG, "onCompleted  " + results.size());
      }
    });*/

    /*appComponent.loadUseCase().load("http://thethaovanhoa.vn/trang-chu.rss", new OnLoadListener() {
      @Override
      public void onStarted() {

      }

      @Override
      public void onNext(String rssUrl, com.qchu.mynews.applogic.load.entity.Feed feed) {

      }

      @Override
      public void onError(Throwable error) {

      }

      @Override
      public void onCompleted() {

      }
    });*/


  }

  public AppComponent appComponent() {
    return appComponent;
  }
}
