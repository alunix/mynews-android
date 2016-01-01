package com.qchu.mynews.common;

import android.content.Context;

import com.qchu.mynews.applogic.load.DefaultLoadStorage;
import com.qchu.mynews.applogic.load.GoogleFeedLoadService;
import com.qchu.mynews.applogic.load.usecase.DefaultLoadUseCase;
import com.qchu.mynews.applogic.load.usecase.LoadService;
import com.qchu.mynews.applogic.load.usecase.LoadStorage;
import com.qchu.mynews.applogic.load.usecase.LoadUseCase;
import com.qchu.mynews.applogic.search.DefaultSearchStorage;
import com.qchu.mynews.applogic.search.GoogleFeedSearchService;
import com.qchu.mynews.applogic.search.usecase.DefaultSearchUseCase;
import com.qchu.mynews.applogic.search.usecase.SearchService;
import com.qchu.mynews.applogic.search.usecase.SearchStorage;
import com.qchu.mynews.applogic.search.usecase.SearchUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Module (includes = GoogleFeedModule.class)
public class UseCaseModule {
  @Provides @Singleton
  SearchService provideSearchService(GoogleFeedSearchService googleFeedSearchService){
    return googleFeedSearchService;
  }

  @Provides @Singleton
  SearchStorage provideSearchStorage(DefaultSearchStorage defaultSearchStorage){
    return defaultSearchStorage;
  }

  @Provides @Singleton
  SearchUseCase provideSearchUseCase(DefaultSearchUseCase defaultSearchUseCase){
    return defaultSearchUseCase;
  }

  @Provides @Singleton
  LoadService provideLoadService(GoogleFeedLoadService googleFeedLoadService){
    return googleFeedLoadService;
  }

  @Provides @Singleton
  LoadStorage provideLoadStorage(DefaultLoadStorage defaultLoadStorage){
    return defaultLoadStorage;
  }

  @Provides @Singleton
  LoadUseCase provideLoadUseCase(DefaultLoadUseCase defaultLoadUseCase){
    return defaultLoadUseCase;
  }
}
