package com.qchu.feedarticle.dagger;

import com.qchu.feedarticle.applogic.domain.article.interactor.ArticleInteractor;
import com.qchu.feedarticle.applogic.domain.article.interactor.DefaultArticleInteractor;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.DefaultFavoriteInteractor;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteInteractor;
import com.qchu.feedarticle.applogic.domain.search.interactor.DefaultSearchInteractor;
import com.qchu.feedarticle.applogic.domain.search.interactor.SearchInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quocdungchu on 30/10/15.
 */
@Module(includes = {RepositoryModule.class, ServiceModule.class, NetworkModule.class})
public class InteractorModule {
	@Provides @Singleton ArticleInteractor provideArticleInteractor(
		DefaultArticleInteractor defaultArticleInteractor){

		return defaultArticleInteractor;
	}

	@Provides @Singleton FavoriteInteractor provideFavoriteInteractor(
		DefaultFavoriteInteractor defaultFavoriteInteractor){

		return defaultFavoriteInteractor;
	}

	@Provides @Singleton
	SearchInteractor provideSearchInteractor(
		DefaultSearchInteractor defaultSearchInteractor){

		return defaultSearchInteractor;
	}
}
