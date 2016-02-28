package com.qchu.mynews.common.dagger.module;

import com.qchu.mynews.applogic.load.FeedlyLoadWebService;
import com.qchu.mynews.applogic.load.webservice.LoadWebService;
import com.qchu.mynews.applogic.search.FeedlySearchWebService;
import com.qchu.mynews.applogic.search.webservice.SearchWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 28/02/16.
 */
@Module(includes = SchedulerModule.class)
public class WebServiceModule {
  @Provides @Singleton
  SearchWebService provideSearchService(FeedlySearchWebService service){
    return service;
  }

  @Provides @Singleton
  LoadWebService provideLoadService(FeedlyLoadWebService service){
    return service;
  }
}
