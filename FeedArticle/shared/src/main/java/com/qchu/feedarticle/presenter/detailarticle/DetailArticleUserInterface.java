package com.qchu.feedarticle.presenter.detailarticle;

import com.qchu.feedarticle.domain.article.entity.Article;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult;

import java.util.List;

/**
 * Created by quocdungchu on 27/09/15.
 */
public interface DetailArticleUserInterface {
	void bindArticles(DetailArticlePresenter detailArticlePresenter, List<Article> articleList);

	void selectIndex(DetailArticlePresenter detailArticlePresenter,
	                 int selectedIndex, boolean animated);

	void updateWithCurrentArticle(DetailArticlePresenter detailArticlePresenter, Article article);

	void updateFavoriteStateOfCurrentArticle(DetailArticlePresenter detailArticlePresenter,
	                                         boolean isInFavorite);

	void showMessageToCompleteUpdateCurrentArticleInFavorite(
		DetailArticlePresenter detailArticlePresenter,
		FavoriteActionResult updateFavoriteActionResult);
}
