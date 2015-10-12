package com.qchu.feedarticle.feature.detailarticle.ui.presenter;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;

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
		ArticleInteractor.UpdateFavoriteActionResult updateFavoriteActionResult);
}
