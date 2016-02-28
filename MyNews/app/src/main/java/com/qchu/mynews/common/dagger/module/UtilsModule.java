package com.qchu.mynews.common.dagger.module;

import com.qchu.common.utils.BroadcastCenter;
import com.qchu.common.utils.Connectivity;
import com.qchu.common.utils.Log;
import com.qchu.mynews.common.dagger.utils.AndroidBroadcastCenter;
import com.qchu.mynews.common.dagger.utils.AndroidConnectivity;
import com.qchu.mynews.common.dagger.utils.AndroidLog;

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
