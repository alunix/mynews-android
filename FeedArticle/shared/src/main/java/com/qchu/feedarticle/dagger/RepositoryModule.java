package com.qchu.feedarticle.dagger;

import com.qchu.feedarticle.applogic.domain.article.interactor.ArticleStorage;
import com.qchu.feedarticle.applogic.domain.article.interactor.LoadService;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteRepository;

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
	FavoriteRepository favoriteRepository;

	public RepositoryModule(LoadService loadService,
	                        ArticleStorage articleStorage,
	                        FavoriteRepository favoriteRepository){

		this.loadService = loadService;
		this.articleStorage = articleStorage;
		this.favoriteRepository = favoriteRepository;
	}

	@Provides @Singleton
	ArticleStorage provideArticleRepository(){
		return this.articleStorage;
	}

	@Provides @Singleton FavoriteRepository provideFavoriteRepository(){
		return this.favoriteRepository;
	}

	@Provides @Singleton
	LoadService provideSourceRepository(){
		return this.loadService;
	}
}
