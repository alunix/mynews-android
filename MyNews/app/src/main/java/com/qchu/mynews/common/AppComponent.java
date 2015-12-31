package com.qchu.mynews.common;

import com.qchu.common.Log;
import com.qchu.googlefeed.search.service.SearchService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
  SearchService searchService();
  Log log();
}
