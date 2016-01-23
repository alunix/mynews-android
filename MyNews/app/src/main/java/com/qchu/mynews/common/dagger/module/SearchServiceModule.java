package com.qchu.mynews.common.dagger.module;


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
  SearchService provideSearchService(GoogleFeedSearchService service){
    return service;
  }
}
