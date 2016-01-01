package com.qchu.mynews.common;

import com.qchu.common.Log;
import com.qchu.googlefeed.load.service.LoadService;
import com.qchu.googlefeed.search.service.SearchService;
import com.qchu.mynews.applogic.load.usecase.LoadUseCase;
import com.qchu.mynews.applogic.search.usecase.SearchUseCase;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
  SearchService searchService();
  LoadService loadService();

  SearchUseCase searchUseCase();
  LoadUseCase loadUseCase();
  Log log();
}
