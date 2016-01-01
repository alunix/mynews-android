package com.qchu.mynews.common;

import android.content.Context;

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
}
