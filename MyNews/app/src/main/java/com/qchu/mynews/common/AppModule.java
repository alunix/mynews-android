package com.qchu.mynews.common;

import android.app.Application;
import android.content.Context;

import com.qchu.common.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
@Module (includes = {
  UseCaseModule.class
})
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
}
