package com.qchu.mynews.common.dagger.module;

import com.qchu.mynews.applogic.load.RssLoadService;
import com.qchu.mynews.applogic.load.usecase.LoadService;
import com.qchu.rss.DefaultRssApi;
import com.qchu.rss.RssApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 07/01/16.
 */
@Module (includes = SchedulerModule.class)
public class LoadServiceModule {

  @Provides @Singleton
  RssApi provideLoadApi(DefaultRssApi api){
    return api;
  }

  @Provides @Singleton
  LoadService provideLoadService(RssLoadService service){
    return service;
  }
}
