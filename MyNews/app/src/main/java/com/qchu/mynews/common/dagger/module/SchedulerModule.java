package com.qchu.mynews.common.dagger.module;

import com.qchu.googlefeed.Constants;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Quoc Dung Chu on 07/01/16.
 */
@Module
public class SchedulerModule {

  private Scheduler networkingScheduler;

  private Scheduler networkingScheduler(){
    if(networkingScheduler == null) {
      networkingScheduler = Schedulers.from(Executors.newFixedThreadPool(4));
    }
    return networkingScheduler;
  }

  @Provides @Singleton @Named(Constants.SCHEDULER_OBSERVED)
  Scheduler provideGoogleFeedObservedOnScheduler(){
    return AndroidSchedulers.mainThread();
  }

  @Provides @Singleton @Named(Constants.SCHEDULER_SUBSCRIBED)
  Scheduler provideGoogleFeedSubscribedOnScheduler(){
    return networkingScheduler();
  }

  @Provides @Singleton @Named(com.qchu.rss.Constants.SCHEDULER_OBSERVED)
  Scheduler provideRssObservedOnScheduler(){
    return AndroidSchedulers.mainThread();
  }

  @Provides @Singleton @Named(com.qchu.rss.Constants.SCHEDULER_SUBSCRIBED)
  Scheduler provideRssSubscribedOnScheduler(){
    return networkingScheduler();
  }
}