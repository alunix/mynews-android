package com.qchu.feedarticle;

import com.qchu.feedarticle.applogic.domain.article.interactor.ArticleInteractor;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteInteractor;
import com.qchu.feedarticle.applogic.domain.search.interactor.SearchInteractor;
import com.qchu.feedarticle.dagger.AppLogicComponent;
import com.qchu.feedarticle.dagger.InteractorModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quocdungchu on 26/11/15.
 */
@Singleton @Component(modules = { AppModule.class, InteractorModule.class })
public interface AppComponent {
  ArticleInteractor articleInteractor();
  FavoriteInteractor favoriteInteractor();
  SearchInteractor searchInterator();
}
