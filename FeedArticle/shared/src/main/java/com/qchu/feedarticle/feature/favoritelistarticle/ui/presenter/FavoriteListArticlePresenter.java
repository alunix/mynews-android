package com.qchu.feedarticle.feature.favoritelistarticle.ui.presenter;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteAction;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteInteractor;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticlePresenter;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleWireframeInterface;

import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public class FavoriteListArticlePresenter extends ListArticlePresenter
	implements FavoriteListArticleUserEventHandler{

	public enum EditState {
		NORMAL,
		EDITING
	}

	List<Article> articleList;
	EditState editState;
	final FavoriteInteractor favoriteInteractor;
	final FavoriteListArticleUserInterface favoriteListArticleUserInterface;

	public static FavoriteListArticlePresenter create(
		FavoriteListArticleUserInterface favoriteListArticleUserInterface,
		ListArticleWireframeInterface listArticleWireframeInterface,
		FavoriteInteractor favoriteInteractor) {

		FavoriteListArticlePresenter favoriteListArticlePresenter =
			new FavoriteListArticlePresenter(
				favoriteListArticleUserInterface,
				listArticleWireframeInterface,
				favoriteInteractor);

		favoriteListArticlePresenter.onCreate();

		return favoriteListArticlePresenter;
	}

	protected FavoriteListArticlePresenter(
		FavoriteListArticleUserInterface favoriteListArticleUserInterface,
		ListArticleWireframeInterface listArticleWireframeInterface,
		FavoriteInteractor favoriteInteractor) {

		super(favoriteListArticleUserInterface, listArticleWireframeInterface);

		this.favoriteInteractor = favoriteInteractor;
		this.favoriteListArticleUserInterface = favoriteListArticleUserInterface;
		this.editState = EditState.NORMAL;
	}

	@Override
	protected void onCreate() {
		this.articleIdList = this.favoriteInteractor.getFavoriteArticleIdsInRepository();
		this.articleList = this.favoriteInteractor.getFavoriteArticlesInRepository();
		this.favoriteListArticleUserInterface.bindArticles(this.articleList);
	}

	@Override
	protected void onDestroy() {

	}

	@Override
	public void onEditEventClick(FavoriteListArticleUserInterface favoriteListArticleUserInterface) {
		if(this.editState == EditState.NORMAL) {
			this.editState = EditState.EDITING;
		} else {
			this.editState = EditState.NORMAL;
		}
		this.favoriteListArticleUserInterface.updateWithEditState(this.editState);
	}

	@Override
	public void onRemoveArticleAtIndex(
		FavoriteListArticleUserInterface favoriteListArticleUserInterface,
		int removeAtIndex) {

		String removedArticleId = this.articleIdList.get(removeAtIndex);

		this.articleIdList.remove(removeAtIndex);
		this.articleList.remove(removeAtIndex);
		this.favoriteInteractor.updateArticleInFavoriteRepository(
			FavoriteAction.REMOVE, removedArticleId);
	}
}
