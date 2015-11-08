package com.qchu.feedarticle.domain.favorite.interactor;

import com.qchu.feedarticle.domain.article.entity.Article;

import java.util.List;

/**
 * Created by quocdungchu on 30/10/15.
 */
public interface FavoriteInteractor {
	List<Article> getFavoriteArticlesInRepository();

	List<String> getFavoriteArticleIdsInRepository();

	boolean isFavoriteArticleInRepository(String articleId);

	FavoriteActionResult updateArticleInFavoriteRepository(
		FavoriteAction updateFavoriteAction,
		String articleId);
}
