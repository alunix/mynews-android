package com.qchu.feedarticle.feature.detailarticle.ui.presenter;

import com.qchu.feedarticle.common.Presenter;
import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteAction;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteActionResult;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteInteractor;

import java.util.List;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DetailArticlePresenter extends Presenter
	implements DetailArticleUserEventHandler {

	List<String> articleIdList;
	int currentIndex;

	ArticleInteractor articleInteractor;
	FavoriteInteractor favoriteInteractor;
	DetailArticleUserInterface detailArticleUserInterface;
	DetailArticleWireframeInterface detailArticleWireframeInterface;

	public static DetailArticlePresenter create(
		ArticleInteractor articleInteractor,
		FavoriteInteractor favoriteInteractor,
		DetailArticleUserInterface detailArticleUserInterface,
		DetailArticleWireframeInterface detailArticleWireframeInterface,
		List<String> articleIdList, int currentIndex) {

		DetailArticlePresenter detailArticlePresenter = new DetailArticlePresenter();
		detailArticlePresenter.articleInteractor = articleInteractor;
		detailArticlePresenter.favoriteInteractor = favoriteInteractor;
		detailArticlePresenter.detailArticleUserInterface = detailArticleUserInterface;
		detailArticlePresenter.detailArticleWireframeInterface = detailArticleWireframeInterface;
		detailArticlePresenter.articleIdList = articleIdList;
		detailArticlePresenter.currentIndex = currentIndex;

		detailArticlePresenter.onCreate();

		return detailArticlePresenter;
	}

	DetailArticlePresenter(){}

	@Override
	protected void onCreate() {
		detailArticleUserInterface.bindArticles(this,
			articleInteractor.getArticleListInRepositoryByArticleIds(articleIdList));
		detailArticleUserInterface.selectIndex(this, currentIndex, false);
		updateCurrentArticleUserInterface();
	}

	@Override
	protected void onDestroy() {

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
