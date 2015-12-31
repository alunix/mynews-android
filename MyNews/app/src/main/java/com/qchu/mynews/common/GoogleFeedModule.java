package com.qchu.mynews.common;

import com.qchu.googlefeed.Constants;
import com.qchu.googlefeed.load.service.DefaultLoadService;
import com.qchu.googlefeed.load.service.LoadService;
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
public class GoogleFeedModule {

  @Provides @Singleton @Named(Constants.SCHEDULER_OBSERVED)
  Scheduler provideObservedOnScheduler(){
    return AndroidSchedulers.mainThread();
  }

  @Provides @Singleton @Named(Constants.SCHEDULER_SUBSCRIBED)
  Scheduler provideSubscribedOnScheduler(){
    return Schedulers.from(Executors.newFixedThreadPool(4));
  }

  @Provides @Singleton
  SearchService provideSearchService(DefaultSearchService defaultSearchService){
    return defaultSearchService;
  }

  @Provides @Singleton
  LoadService provideLoadService(DefaultLoadService defaultLoadService){
    return defaultLoadService;
  }
}
