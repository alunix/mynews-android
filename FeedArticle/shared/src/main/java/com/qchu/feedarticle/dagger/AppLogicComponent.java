package com.qchu.feedarticle.dagger;

import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.detailarticle.ui.presenter.DetailArticlePresenter;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteInteractor;
import com.qchu.feedarticle.feature.favoritelistarticle.ui.presenter.FavoriteListArticlePresenter;
import com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter.RefreshListArticlePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quocdungchu on 25/10/15.
 */
@Singleton
@Component(modules = RepositoryModule.class)
public interface AppLogicComponent {
	ArticleInteractor articleInteractor();
	FavoriteInteractor favoriteInteractor();
}
