package com.qchu.mynews.common;

import android.app.Application;
import android.content.Context;

import com.qchu.common.Log;
import com.qchu.googlefeed.search.service.DefaultSearchService;
import com.qchu.googlefeed.search.service.SearchService;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
@Module
public class AppModule {
  Application application;

  public AppModule(Application application){
    this.application = application;
  }

  @Provides @Singleton Context provideContext(){
    return this.application;
  }

  @Provides @Singleton Log provideLog(AndroidLog androidLog){
    return androidLog;
  }

  @Provides @Singleton @Named("observedOn") Scheduler provideObservedOnScheduler(){
    return AndroidSchedulers.mainThread();
  }

  @Provides @Singleton @Named("subscribedOn") Scheduler provideSubscribedOnScheduler(){
    return Schedulers.from(Executors.newFixedThreadPool(4));
  }

  @Provides @Singleton
  SearchService provideSearchService(DefaultSearchService defaultSearchService){
    return defaultSearchService;
  }

}
