package com.qchu.feedarticle.dagger;

import com.qchu.feedarticle.applogic.domain.article.interactor.ArticleStorage;
import com.qchu.feedarticle.applogic.domain.article.interactor.LoadService;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quocdungchu on 25/10/15.
 */
@Module
public class RepositoryModule {

	LoadService loadService;
	ArticleStorage articleStorage;
	FavoriteStorage favoriteStorage;

	public RepositoryModule(LoadService loadService,
	                        ArticleStorage articleStorage,
	                        FavoriteStorage favoriteStorage){

		this.loadService = loadService;
		this.articleStorage = articleStorage;
		this.favoriteStorage = favoriteStorage;
	}

	@Provides @Singleton
	ArticleStorage provideArticleRepository(){
		return this.articleStorage;
	}

	@Provides @Singleton
	FavoriteStorage provideFavoriteRepository(){
		return this.favoriteStorage;
	}

	@Provides @Singleton
	LoadService provideSourceRepository(){
		return this.loadService;
	}
}
