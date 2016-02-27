package com.qchu.mynews.common.dagger;

import com.qchu.common.Log;
import com.qchu.mynews.applogic.load.usecase.LoadUseCase;
import com.qchu.mynews.applogic.recommanded.usecase.RecommandedUseCase;
import com.qchu.mynews.applogic.search.usecase.SearchUseCase;
import com.qchu.mynews.common.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
  RecommandedUseCase recommandedUseCase();
  SearchUseCase searchUseCase();
  LoadUseCase loadUseCase();
  Log log();
}
