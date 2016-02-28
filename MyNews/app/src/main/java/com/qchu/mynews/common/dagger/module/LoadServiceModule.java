package com.qchu.mynews.common.dagger.module;

import com.qchu.mynews.applogic.load.FeedlyLoadService;
import com.qchu.mynews.applogic.load.usecase.LoadService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 07/01/16.
 */
@Module (includes = SchedulerModule.class)
public class LoadServiceModule {

  @Provides @Singleton
  LoadService provideLoadService(FeedlyLoadService service){
    return service;
  }
}
