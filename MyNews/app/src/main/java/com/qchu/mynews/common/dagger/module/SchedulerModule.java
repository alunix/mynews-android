package com.qchu.mynews.common.dagger.module;

import com.qchu.mynews.applogic.Constants;
import com.qchu.mynews.applogic.common.Priority;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ronshapiro.rx.priority.PriorityScheduler;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Quoc Dung Chu on 07/01/16.
 */
@Module
public class SchedulerModule {

  private PriorityScheduler networkingScheduler;
  private PriorityScheduler databaseScheduler;

  private PriorityScheduler networkingScheduler(){
    if(networkingScheduler == null) {
      networkingScheduler = PriorityScheduler.withConcurrency(4);
    }
    return networkingScheduler;
  }

  private PriorityScheduler databaseScheduler(){
    if(databaseScheduler == null) {
      databaseScheduler = PriorityScheduler.withConcurrency(1);
    }
    return databaseScheduler;
  }

  @Provides @Singleton @Named(Constants.SCHEDULER_MAIN_THREAD)
  Scheduler provideMainThreadScheduler(){
    return AndroidSchedulers.mainThread();
  }

  @Provides @Singleton @Named(Constants.SCHEDULER_NETWORK)
  PriorityScheduler provideNetworkScheduler(){
    return networkingScheduler();
  }

  @Provides @Singleton @Named(Constants.SCHEDULER_DATABASE)
  PriorityScheduler provideDatabaseScheduler(){
    return databaseScheduler();
  }
}
