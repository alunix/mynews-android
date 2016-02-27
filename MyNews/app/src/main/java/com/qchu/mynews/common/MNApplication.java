package com.qchu.mynews.common;

import android.app.Application;

import com.qchu.mynews.applogic.recommanded.usecase.OnSynchronizeListener;
import com.qchu.mynews.common.dagger.AppComponent;
import com.qchu.mynews.common.dagger.DaggerAppComponent;
import com.qchu.mynews.common.dagger.module.AppModule;

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
      Lists.newArrayList("http://thethaovanhoa.vn/trang-chu.rss"),
      new OnSynchronizeListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onNext() {

        }

        @Override
        public void onFinish() {

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
