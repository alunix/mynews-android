package com.qchu.feedarticle.presenter.favoritelistarticle;

import com.qchu.feedarticle.domain.article.entity.Article;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteInteractor;
import com.qchu.feedarticle.presenter.listarticle.ListArticlePresenter;
import com.qchu.feedarticle.presenter.listarticle.ListArticleWireframeInterface;

import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public class FavoriteListArticlePresenter extends ListArticlePresenter
	implements FavoriteListArticleUserEventHandler {

	public enum EditState {
		NORMAL,
		EDITING
	}

	List<Article> articleList;
	EditState editState;
	final FavoriteInteractor favoriteInteractor;
	final FavoriteListArticleUserInterface favoriteListArticleUserInterface;

	protected FavoriteListArticlePresenter(
		FavoriteInteractor favoriteInteractor,
		FavoriteListArticleUserInterface favoriteListArticleUserInterface,
		ListArticleWireframeInterface listArticleWireframeInterface) {

		super(favoriteListArticleUserInterface, listArticleWireframeInterface);

		this.favoriteInteractor = favoriteInteractor;
		this.favoriteListArticleUserInterface = favoriteListArticleUserInterface;
		this.editState = EditState.NORMAL;
	}

	@Override
	public void onCreate() {
		this.articleIdList = this.favoriteInteractor.getFavoriteArticleIdsInRepository();
		this.articleList = this.favoriteInteractor.getFavoriteArticlesInRepository();
		this.favoriteListArticleUserInterface.bindArticles(this.articleList);
	}

	@Override
	public void onDestroy() {

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
