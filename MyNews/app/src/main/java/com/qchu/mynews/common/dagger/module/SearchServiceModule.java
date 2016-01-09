package com.qchu.mynews.common.dagger.module;


import com.qchu.googlefeed.search.service.DefaultSearchApi;
import com.qchu.googlefeed.search.service.SearchApi;
import com.qchu.mynews.applogic.search.FeedlySearchService;
import com.qchu.mynews.applogic.search.GoogleFeedSearchService;
import com.qchu.mynews.applogic.search.usecase.SearchService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 07/01/16.
 */
@Module (includes = SchedulerModule.class)
public class SearchServiceModule {
  @Provides @Singleton
  SearchApi provideSearchApi(DefaultSearchApi api){
    return api;
  }

  @Provides @Singleton
  SearchService provideSearchService(FeedlySearchService service){
    return service;
  }
}
