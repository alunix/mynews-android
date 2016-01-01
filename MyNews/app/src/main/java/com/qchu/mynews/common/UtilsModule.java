package com.qchu.mynews.common;

import com.qchu.common.BroadcastCenter;
import com.qchu.common.Connectivity;
import com.qchu.common.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Module
public class UtilsModule {
  @Provides @Singleton
  Connectivity provideConnectivity(AndroidConnectivity androidConnectivity){
    return androidConnectivity;
  }

  @Provides @Singleton
  BroadcastCenter provideBroadcastCenter(AndroidBroadcastCenter androidBroadcastCenter){
    return androidBroadcastCenter;
  }

  @Provides @Singleton
  Log provideLog(AndroidLog androidLog){
    return androidLog;
  }
}
