package com.qchu.mynews.common;

import android.app.Application;
import android.provider.Settings;

import com.qchu.googlefeed.load.entity.Feed;
import com.qchu.googlefeed.load.service.LoadService;
import com.qchu.googlefeed.search.entity.Entry;
import com.qchu.googlefeed.search.service.SearchService;
import com.qchu.mynews.applogic.load.usecase.OnLoadListener;
import com.qchu.mynews.applogic.search.entity.Result;
import com.qchu.mynews.applogic.search.usecase.OnSearchListener;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public class MNApplication extends Application {

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

      }

      @Override
      public void onNext(String keyword, Result result) {

      }

      @Override
      public void onError(Throwable error) {

      }

      @Override
      public void onCompleted() {

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
