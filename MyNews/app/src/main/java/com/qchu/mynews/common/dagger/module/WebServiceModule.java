package com.qchu.mynews.common.dagger.module;

import com.qchu.mynews.applogic.load.FeedlyLoadWebService;
import com.qchu.mynews.applogic.load.webservice.LoadWebService;
import com.qchu.mynews.applogic.search.FeedlySearchService;
import com.qchu.mynews.applogic.search.usecase.SearchService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 28/02/16.
 */
@Module(includes = SchedulerModule.class)
public class WebServiceModule {
  @Provides @Singleton
  SearchService provideSearchService(FeedlySearchService service){
    return service;
  }

  @Provides @Singleton
  LoadWebService provideLoadService(FeedlyLoadWebService service){
    return service;
  }
}
