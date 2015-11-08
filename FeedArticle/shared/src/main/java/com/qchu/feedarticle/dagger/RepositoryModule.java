package com.qchu.feedarticle.dagger;

import com.qchu.feedarticle.domain.article.interactor.ArticleRepository;
import com.qchu.feedarticle.domain.article.interactor.SourceRepository;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quocdungchu on 25/10/15.
 */
@Module
public class RepositoryModule {

	SourceRepository sourceRepository;
	ArticleRepository articleRepository;
	FavoriteRepository favoriteRepository;

	public RepositoryModule(SourceRepository sourceRepository,
	                        ArticleRepository articleRepository,
	                        FavoriteRepository favoriteRepository){

		this.sourceRepository = sourceRepository;
		this.articleRepository = articleRepository;
		this.favoriteRepository = favoriteRepository;
	}

	@Provides @Singleton ArticleRepository provideArticleRepository(){
		return this.articleRepository;
	}

	@Provides @Singleton FavoriteRepository provideFavoriteRepository(){
		return this.favoriteRepository;
	}

	@Provides @Singleton SourceRepository provideSourceRepository(){
		return this.sourceRepository;
	}
}
