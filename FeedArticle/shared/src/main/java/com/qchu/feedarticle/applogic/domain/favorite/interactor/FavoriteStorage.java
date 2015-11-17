package com.qchu.feedarticle.applogic.domain.favorite.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Article;

import java.util.List;

/**
 * Created by quocdungchu on 17/10/15.
 */
public interface FavoriteStorage {
	List<Article> favoriteArticles();
	List<String> favoriteArticleIds();
	boolean isFavoriteArticle(String articleId);
	FavoriteActionResult updateArticle(FavoriteAction favoriteAction, String articleId);
}
