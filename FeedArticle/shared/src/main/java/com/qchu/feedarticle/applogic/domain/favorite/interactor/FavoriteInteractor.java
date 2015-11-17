package com.qchu.feedarticle.applogic.domain.favorite.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Article;

import java.util.List;

/**
 * Created by quocdungchu on 30/10/15.
 */
public interface FavoriteInteractor {
	List<Article> favoriteArticles();

	List<String> favoriteArticleIds();

	boolean isFavorite(String articleId);

	FavoriteActionResult updateArticle(FavoriteAction updateFavoriteAction, String articleId);
}
