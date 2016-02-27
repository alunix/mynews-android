package com.qchu.mynews.common.dagger.module;

import com.qchu.mynews.applogic.Constants;

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
  private Scheduler databaseScheduler;

  private Scheduler networkingScheduler(){
    if(networkingScheduler == null) {
      networkingScheduler = Schedulers.from(Executors.newFixedThreadPool(4));
    }
    return networkingScheduler;
  }

  private Scheduler databaseScheduler(){
    if(databaseScheduler == null) {
      databaseScheduler = Schedulers.from(Executors.newFixedThreadPool(1));
    }
    return databaseScheduler;
  }

  @Provides @Singleton @Named(Constants.SCHEDULER_MAIN_THREAD)
  Scheduler provideMainThreadScheduler(){
    return AndroidSchedulers.mainThread();
  }

  @Provides @Singleton @Named(Constants.SCHEDULER_NETWORK)
  Scheduler provideNetworkScheduler(){
    return networkingScheduler();
  }

  @Provides @Singleton @Named(Constants.SCHEDULER_DATABASE)
  Scheduler provideDatabaseScheduler(){
    return databaseScheduler();
  }
}
