package com.qchu.feedarticle.feature.favorite.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;

import java.util.List;

/**
 * Created by quocdungchu on 17/10/15.
 */
public interface FavoriteRepository {
	List<Article> getFavoriteArticles();
	List<String> getFavoriteArticleIds();
	boolean isFavoriteArticle(String articleId);
	FavoriteActionResult updateArticleInFavorite(FavoriteAction favoriteAction, String articleId);
}
