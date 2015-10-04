package com.qchu.feedarticle.feature.detailarticle.ui.presenter;

import com.qchu.feedarticle.common.Presenter;
import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;

import java.util.List;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DetailArticlePresenter extends Presenter
	implements DetailArticleUserInterfaceEventHandler {

	List<String> mArticleIdList;
	int mCurrentIndex;

	ArticleInteractor mArticleInteractor;
	DetailArticleUserInterface mDetailArticleUserInterface;
	DetailArticleWireframeInterface mDetailArticleWireframeInterface;

	public static DetailArticlePresenter create(
		ArticleInteractor articleInteractor,
		DetailArticleUserInterface detailArticleUserInterface,
		DetailArticleWireframeInterface detailArticleWireframeInterface,
		List<String> articleIdList, int currentIndex) {

		DetailArticlePresenter detailArticlePresenter = new DetailArticlePresenter();
		detailArticlePresenter.mArticleInteractor = articleInteractor;
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
	}

	@Override
	protected void onDestroy() {

	}

	@Override
	public void onScrollToPageIndexEvent(DetailArticleUserInterface detailArticleUserInterface,
	                                     int pageIndex) {

		mCurrentIndex = pageIndex;
		mDetailArticleUserInterface.updateWithCurrentArticle(this,
			mArticleInteractor.getArticleInRepositoryByArticleId(currentArticleId()));
	}

	@Override
	public void onAddOrRemoveCurrentArticleInFavoriteEvent(
		DetailArticlePresenter detailArticlePresenter) {

		ArticleInteractor.UpdateFavoriteActionResult result;
		if(mArticleInteractor.isFavoriteArticleInRepository(currentArticleId())) {
			result = mArticleInteractor.updateArticleInFavoriteRepository(
				ArticleInteractor.UpdateFavoriteAction.REMOVE, currentArticleId());
		} else {
			result = mArticleInteractor.updateArticleInFavoriteRepository(
				ArticleInteractor.UpdateFavoriteAction.ADD, currentArticleId());
		}

		mDetailArticleUserInterface.updateWhenCurrentArticleFinishUpdateInFavorite(this, result);
		mDetailArticleUserInterface.showMessageToCompleteUpdateCurrentArticleInFavorite(this, result);
	}

	@Override
	public void onShareCurrentArticleEvent(DetailArticlePresenter detailArticlePresenter) {

	}

	String currentArticleId() {
		return mArticleIdList.get(mCurrentIndex);
	}

}
