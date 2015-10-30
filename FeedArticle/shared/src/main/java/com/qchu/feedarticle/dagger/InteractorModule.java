package com.qchu.feedarticle.dagger;

import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.article.applogic.interactor.DefaultArticleInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quocdungchu on 30/10/15.
 */
@Module(includes = RepositoryModule.class)
public class InteractorModule {
	@Provides @Singleton ArticleInteractor provideArticleInteractor(
		DefaultArticleInteractor defaultArticleInteractor){

		return defaultArticleInteractor;
	}
}
