package com.qchu.feedarticle;

import android.app.Application;
import android.content.Context;

import com.qchu.once.shared.connectivity.Connectivity;
import com.qchu.once.shared.network.interactor.ErrorMessageProvider;
import com.qchu.once.shared.network.interactor.NetworkAdapter;
import com.qchu.once.utils.network.adapter.retrofit.RetrofitNetworkAdapter;
import com.squareup.okhttp.Cache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quocdungchu on 26/11/15.
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

  @Provides @Singleton Connectivity provideConnectivity(
    DefaultConnectivity defaultConnectivity){

    return defaultConnectivity;
  }

  @Provides @Singleton
  ErrorMessageProvider provideErrorMessageProvider(
    DefaultErrorMessageProvider defaultErrorMessageProvider){

    return defaultErrorMessageProvider;
  }

  @Provides @Singleton
  NetworkAdapter provideNetworkAdapter(
    RetrofitNetworkAdapter retrofitNetworkAdapter){

    return retrofitNetworkAdapter;
  }

  @Provides @Singleton
  Cache provideCache(){
    return null;
  }

  @Provides @Singleton
  com.qchu.feedarticle.applogic.domain.search.interactor.ErrorMessageProvider provideSearchErrorMessageProvider(
    DefaultSearchErrorMessageProvider defaultSearchErrorMessageProvider){

    return defaultSearchErrorMessageProvider;
  }
}
