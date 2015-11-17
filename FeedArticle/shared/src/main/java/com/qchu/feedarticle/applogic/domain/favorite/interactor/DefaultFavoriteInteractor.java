package com.qchu.feedarticle.applogic.domain.favorite.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Article;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by quocdungchu on 17/10/15.
 */
@Singleton
public class DefaultFavoriteInteractor implements FavoriteInteractor {

	private final FavoriteStorage favoriteStorage;

	@Inject
	public DefaultFavoriteInteractor(FavoriteStorage favoriteStorage){
		this.favoriteStorage = favoriteStorage;
	}

	@Override
	public List<Article> favoriteArticles() {
		return this.favoriteStorage.favoriteArticles();
	}

	@Override
	public List<String> favoriteArticleIds(){
		return this.favoriteStorage.favoriteArticleIds();
	}

	@Override
	public boolean isFavorite(String articleId) {
		return this.favoriteStorage.isFavoriteArticle(articleId);
	}

	@Override
	public FavoriteActionResult updateArticle(
		FavoriteAction updateFavoriteAction, String articleId) {
		return this.favoriteStorage.updateArticle(updateFavoriteAction, articleId);
	}
}
