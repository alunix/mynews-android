package com.qchu.mynews.common.dagger.module;

import com.qchu.mynews.applogic.load.usecase.DefaultLoadUseCase;
import com.qchu.mynews.applogic.load.usecase.LoadUseCase;
import com.qchu.mynews.applogic.recommanded.usecase.DefaultRecommandedUseCase;
import com.qchu.mynews.applogic.recommanded.usecase.RecommandedUseCase;
import com.qchu.mynews.applogic.search.usecase.DefaultSearchUseCase;
import com.qchu.mynews.applogic.search.usecase.SearchUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Module (includes = {
  WebServiceModule.class,
  StorageModule.class,
})
public class UseCaseModule {

  @Provides @Singleton
  RecommandedUseCase provideRecommandedUseCase(DefaultRecommandedUseCase defaultRecommandedUseCase){
    return defaultRecommandedUseCase;
  }

  @Provides @Singleton
  SearchUseCase provideSearchUseCase(DefaultSearchUseCase defaultSearchUseCase){
    return defaultSearchUseCase;
  }

  @Provides @Singleton
  LoadUseCase provideLoadUseCase(DefaultLoadUseCase defaultLoadUseCase){
    return defaultLoadUseCase;
  }
}
