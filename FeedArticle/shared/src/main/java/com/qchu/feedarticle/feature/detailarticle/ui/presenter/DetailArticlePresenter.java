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
	implements DetailArticleUserInterfaceEventHandler {

	List<String> mArticleIdList;
	int mCurrentIndex;

	ArticleInteractor mArticleInteractor;
	FavoriteInteractor favoriteInteractor;
	DetailArticleUserInterface mDetailArticleUserInterface;
	DetailArticleWireframeInterface mDetailArticleWireframeInterface;

	public static DetailArticlePresenter create(
		ArticleInteractor articleInteractor,
		FavoriteInteractor favoriteInteractor,
		DetailArticleUserInterface detailArticleUserInterface,
		DetailArticleWireframeInterface detailArticleWireframeInterface,
		List<String> articleIdList, int currentIndex) {

		DetailArticlePresenter detailArticlePresenter = new DetailArticlePresenter();
		detailArticlePresenter.mArticleInteractor = articleInteractor;
		detailArticlePresenter.favoriteInteractor = favoriteInteractor;
		detailArticlePresenter.mDetailArticleUserInterface = detailArticleUserInterface;
		detailArticlePresenter.mDetailArticleWireframeInterface = detailArticleWireframeInterface;
		detailArticlePresenter.mArticleIdList = articleIdList;
		detailArticlePresenter.mCurrentIndex = currentIndex;

		detailArticlePresenter.onCreate();

		return detailArticlePresenter;
	}

	DetailArticlePresenter(){}

	@Override
	protected void onCreate() {
		mDetailArticleUserInterface.bindArticles(this,
			mArticleInteractor.getArticleListInRepositoryByArticleIds(mArticleIdList));
		mDetailArticleUserInterface.selectIndex(this, mCurrentIndex, false);
		updateCurrentArticleUserInterface();
	}

	@Override
	protected void onDestroy() {

	}

	@Override
	public void onPageSelectedEvent(DetailArticleUserInterface detailArticleUserInterface,
	                                int pageIndex) {

		mCurrentIndex = pageIndex;
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

		mDetailArticleUserInterface.updateFavoriteStateOfCurrentArticle(
			this, this.favoriteInteractor.isFavoriteArticleInRepository(currentArticleId()));

		mDetailArticleUserInterface.showMessageToCompleteUpdateCurrentArticleInFavorite(this, result);
	}

	@Override
	public void onShareCurrentArticleEvent(DetailArticlePresenter detailArticlePresenter) {

	}

	void updateCurrentArticleUserInterface(){
		mDetailArticleUserInterface.updateWithCurrentArticle(this,
			mArticleInteractor.getArticleInRepositoryByArticleId(currentArticleId()));
		mDetailArticleUserInterface.updateFavoriteStateOfCurrentArticle(
			this, this.favoriteInteractor.isFavoriteArticleInRepository(currentArticleId()));
	}

	String currentArticleId() {
		return mArticleIdList.get(mCurrentIndex);
	}

}
