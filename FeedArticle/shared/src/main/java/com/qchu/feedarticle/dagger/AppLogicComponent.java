package com.qchu.feedarticle.dagger;

import com.qchu.feedarticle.applogic.domain.article.interactor.ArticleInteractor;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteInteractor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quocdungchu on 25/10/15.
 */
@Singleton
@Component(modules = InteractorModule.class)
public interface AppLogicComponent {
	ArticleInteractor articleInteractor();
	FavoriteInteractor favoriteInteractor();
}
