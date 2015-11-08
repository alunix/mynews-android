package com.qchu.feedarticle.dagger;

import com.qchu.feedarticle.domain.article.interactor.ArticleInteractor;
import com.qchu.feedarticle.domain.article.interactor.DefaultArticleInteractor;
import com.qchu.feedarticle.domain.favorite.interactor.DefaultFavoriteInteractor;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteInteractor;
import com.qchu.once.shared.network.interactor.DefaultNetworkInteractor;
import com.qchu.once.shared.network.interactor.NetworkInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quocdungchu on 30/10/15.
 */
@Module(includes = {RepositoryModule.class, ServiceModule.class})
public class InteractorModule {
	@Provides @Singleton ArticleInteractor provideArticleInteractor(
		DefaultArticleInteractor defaultArticleInteractor){

		return defaultArticleInteractor;
	}

	@Provides @Singleton FavoriteInteractor provideFavoriteInteractor(
		DefaultFavoriteInteractor defaultFavoriteInteractor){

		return defaultFavoriteInteractor;
	}
}
