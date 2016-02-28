package com.qchu.mynews.common.dagger.module;

import com.qchu.mynews.applogic.common.storage.ArticleStorage;
import com.qchu.mynews.applogic.common.storage.DefaultArticleStorage;
import com.qchu.mynews.applogic.search.storage.DefaultSearchStorage;
import com.qchu.mynews.applogic.search.storage.SearchStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 28/02/16.
 */
@Module(includes = {
  OrmLiteModule.class,
})
public class StorageModule {
  @Provides
  @Singleton
  ArticleStorage provideArticleStorage(DefaultArticleStorage defaultArticleStorage){
    return defaultArticleStorage;
  }

  @Provides @Singleton
  SearchStorage provideSearchStorage(DefaultSearchStorage defaultSearchStorage){
    return defaultSearchStorage;
  }


}
