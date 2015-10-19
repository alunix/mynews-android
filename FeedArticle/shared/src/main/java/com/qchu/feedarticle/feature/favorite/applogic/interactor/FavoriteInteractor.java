package com.qchu.feedarticle.feature.favorite.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;

import java.util.List;

/**
 * Created by quocdungchu on 17/10/15.
 */
public class FavoriteInteractor {

	FavoriteRepository favoriteRepository;

	public static FavoriteInteractor create(FavoriteRepository favoriteRepository) {
		FavoriteInteractor favoriteInteractor = new FavoriteInteractor();
		favoriteInteractor.favoriteRepository = favoriteRepository;

		return favoriteInteractor;
	}

	FavoriteInteractor(){}

	public List<Article> getFavoriteArticlesInRepository() {
		return this.favoriteRepository.getFavoriteArticles();
	}

	public boolean isFavoriteArticleInRepository(String articleId) {
		return this.favoriteRepository.isFavoriteArticle(articleId);
	}

	public FavoriteActionResult updateArticleInFavoriteRepository(
		FavoriteAction updateFavoriteAction, String articleId) {
		return this.favoriteRepository.updateArticleInFavorite(updateFavoriteAction, articleId);
	}
}
