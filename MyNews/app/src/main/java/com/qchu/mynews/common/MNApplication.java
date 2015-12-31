package com.qchu.mynews.common;

import android.app.Application;
import android.provider.Settings;

import com.qchu.googlefeed.load.entity.Feed;
import com.qchu.googlefeed.load.service.LoadService;
import com.qchu.googlefeed.search.entity.Entry;
import com.qchu.googlefeed.search.service.SearchService;

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

    appComponent.searchService().search("bongda", new SearchService.OnSearchListener() {

      @Override
      public void onStarted(String keyword) {
        appComponent.log().d("search", "onStarted " + keyword);
      }

      @Override
      public void onNext(String keyword, List<Entry> entries) {
        appComponent.log().d("search", "onNext " + keyword);

      }

      @Override
      public void onError(String keyword, Throwable error) {
        appComponent.log().d("search", "onError " + keyword+ " , "+error.getLocalizedMessage());

      }

      @Override
      public void onCompleted(String keyword) {
        appComponent.log().d("search", "onCompleted " + keyword);
      }
    });

    appComponent.loadService().load("http://www.thethaovanhoa.net/feed",
      new LoadService.OnLoadListener() {
      @Override
      public void onStarted(String rssUrl) {

      }

      @Override
      public void onNext(String rssUrl, Feed feed) {

      }

      @Override
      public void onError(String rssUrl, Throwable error) {

      }

      @Override
      public void onCompleted(String rssUrl) {

      }
    });
  }

  public AppComponent appComponent() {
    return appComponent;
  }
}
