package com.qchu.mynews.common;

import android.app.Application;
import android.provider.Settings;

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
      public void onSearch(String keyword, List<Entry> entries) {

      }
    });
  }

  public AppComponent appComponent() {
    return appComponent;
  }
}
