package com.qchu.mynews.common.dagger.module;

import com.qchu.mynews.applogic.load.FeedlyLoadWebService;
import com.qchu.mynews.applogic.load.webservice.LoadWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 07/01/16.
 */
@Module (includes = SchedulerModule.class)
public class LoadServiceModule {

  @Provides @Singleton
  LoadWebService provideLoadService(FeedlyLoadWebService service){
    return service;
  }
}
