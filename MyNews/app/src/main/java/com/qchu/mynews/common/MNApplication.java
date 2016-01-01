package com.qchu.mynews.common;

import android.app.Application;

import com.qchu.mynews.applogic.database.model.DbResult;
import com.qchu.mynews.applogic.load.usecase.OnLoadListener;
import com.qchu.mynews.applogic.search.entity.Result;
import com.qchu.mynews.applogic.search.usecase.OnSearchListener;
import com.qchu.mynews.common.dagger.AppComponent;
import com.qchu.mynews.common.dagger.DaggerAppComponent;
import com.qchu.mynews.common.dagger.module.AppModule;

import java.util.List;

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

    appComponent.searchUseCase().search("thethaovanhoa", new OnSearchListener() {
      @Override
      public void onStarted() {
        List<Result> results = appComponent.searchUseCase().resultsWithNumberOfDayAgo(1);
        appComponent.log().d(TAG, "onStarted  " + results.toString());
      }

      @Override
      public void onNext(String keyword, Result result) {

      }

      @Override
      public void onError(Throwable error) {

      }

      @Override
      public void onCompleted() {
        List<Result> results = appComponent.searchUseCase().resultsWithNumberOfDayAgo(1);
        appComponent.log().d(TAG, "onCompleted  " + results.toString());
      }
    });

    appComponent.loadUseCase().load("http://www.thethaovanhoa.net/feed", new OnLoadListener() {
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
    });
  }

  public AppComponent appComponent() {
    return appComponent;
  }
}
