package com.qchu.feedarticle.ui.presenter.detailarticle;

import com.qchu.feedarticle.applogic.domain.article.interactor.ArticleInteractor;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteAction;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteActionResult;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteInteractor;

import java.util.List;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DetailArticlePresenter
	implements com.qchu.feedarticle.ui.presenter.common.Presenter, DetailArticleUserEventHandler {

	final ArticleInteractor articleInteractor;
	final FavoriteInteractor favoriteInteractor;
	final DetailArticleUserInterface detailArticleUserInterface;
	final com.qchu.feedarticle.ui.presenter.detailarticle.DetailArticleWireframeInterface detailArticleWireframeInterface;

	List<String> articleIdList;
	int currentIndex;

	public DetailArticlePresenter(
		ArticleInteractor articleInteractor,
		FavoriteInteractor favoriteInteractor,
		DetailArticleUserInterface detailArticleUserInterface,
		com.qchu.feedarticle.ui.presenter.detailarticle.DetailArticleWireframeInterface detailArticleWireframeInterface,
		List<String> articleIdList,
		int currentIndex){

		this.articleInteractor = articleInteractor;
		this.favoriteInteractor = favoriteInteractor;

		this.detailArticleUserInterface = detailArticleUserInterface;
		this.detailArticleWireframeInterface = detailArticleWireframeInterface;

		this.articleIdList = articleIdList;
		this.currentIndex = currentIndex;
	}

	@Override
	public void onCreate() {
		detailArticleUserInterface.bindArticles(this,
			articleInteractor.articlesByArticleIds(articleIdList));
		detailArticleUserInterface.selectIndex(this, currentIndex, false);
		updateCurrentArticleUserInterface();
	}

	@Override
	public void onDestroy() {

	}

	@Override
	public void onPageSelectedEvent(DetailArticleUserInterface detailArticleUserInterface,
	                                int pageIndex) {

		currentIndex = pageIndex;
		updateCurrentArticleUserInterface();
	}

	@Override
	public void onAddOrRemoveCurrentArticleInFavoriteEvent(
		DetailArticleUserInterface detailArticleUserInterface) {

		FavoriteActionResult result;
		if(this.favoriteInteractor.isFavorite(currentArticleId())) {
			result = this.favoriteInteractor.updateArticle(
				FavoriteAction.REMOVE, currentArticleId());
		} else {
			result = this.favoriteInteractor.updateArticle(
				FavoriteAction.ADD, currentArticleId());
		}

		this.detailArticleUserInterface.updateFavoriteStateOfCurrentArticle(
			this, this.favoriteInteractor.isFavorite(currentArticleId()));

		this.detailArticleUserInterface.showMessageToCompleteUpdateCurrentArticleInFavorite(this, result);
	}

	@Override
	public void onShareCurrentArticleEvent(DetailArticlePresenter detailArticlePresenter) {

	}

	void updateCurrentArticleUserInterface(){
		detailArticleUserInterface.updateWithCurrentArticle(this,
			articleInteractor.articlesByArticleId(currentArticleId()));
		detailArticleUserInterface.updateFavoriteStateOfCurrentArticle(
			this, this.favoriteInteractor.isFavorite(currentArticleId()));
	}

	String currentArticleId() {
		return articleIdList.get(currentIndex);
	}

}
