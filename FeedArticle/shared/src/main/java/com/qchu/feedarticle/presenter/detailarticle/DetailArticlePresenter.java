package com.qchu.feedarticle.presenter.detailarticle;

import com.qchu.feedarticle.presenter.common.Presenter;
import com.qchu.feedarticle.domain.article.interactor.ArticleInteractor;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteInteractor;

import java.util.List;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DetailArticlePresenter
	implements Presenter, DetailArticleUserEventHandler {

	final ArticleInteractor articleInteractor;
	final FavoriteInteractor favoriteInteractor;
	final DetailArticleUserInterface detailArticleUserInterface;
	final DetailArticleWireframeInterface detailArticleWireframeInterface;

	List<String> articleIdList;
	int currentIndex;

	public DetailArticlePresenter(
		ArticleInteractor articleInteractor,
		FavoriteInteractor favoriteInteractor,
		DetailArticleUserInterface detailArticleUserInterface,
		DetailArticleWireframeInterface detailArticleWireframeInterface,
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
			articleInteractor.getArticleListInRepositoryByArticleIds(articleIdList));
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
		if(this.favoriteInteractor.isFavoriteArticleInRepository(currentArticleId())) {
			result = this.favoriteInteractor.updateArticleInFavoriteRepository(
				FavoriteAction.REMOVE, currentArticleId());
		} else {
			result = this.favoriteInteractor.updateArticleInFavoriteRepository(
				FavoriteAction.ADD, currentArticleId());
		}

		this.detailArticleUserInterface.updateFavoriteStateOfCurrentArticle(
			this, this.favoriteInteractor.isFavoriteArticleInRepository(currentArticleId()));

		this.detailArticleUserInterface.showMessageToCompleteUpdateCurrentArticleInFavorite(this, result);
	}

	@Override
	public void onShareCurrentArticleEvent(DetailArticlePresenter detailArticlePresenter) {

	}

	void updateCurrentArticleUserInterface(){
		detailArticleUserInterface.updateWithCurrentArticle(this,
			articleInteractor.getArticleInRepositoryByArticleId(currentArticleId()));
		detailArticleUserInterface.updateFavoriteStateOfCurrentArticle(
			this, this.favoriteInteractor.isFavoriteArticleInRepository(currentArticleId()));
	}

	String currentArticleId() {
		return articleIdList.get(currentIndex);
	}

}
