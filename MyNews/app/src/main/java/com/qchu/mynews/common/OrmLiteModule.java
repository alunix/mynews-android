package com.qchu.mynews.common;

import com.qchu.googlefeed.search.service.DefaultSearchService;
import com.qchu.googlefeed.search.service.SearchService;
import com.qchu.mynews.applogic.database.DefaultOrmliteHelper;
import com.qchu.mynews.applogic.database.OrmliteHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Module (includes = UtilsModule.class)
public class OrmLiteModule {
  @Provides @Singleton
  OrmliteHelper provideOrmliteHelper(DefaultOrmliteHelper defaultOrmliteHelper){
    return defaultOrmliteHelper;
  }
}
