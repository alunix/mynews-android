package com.qchu.feedarticle;

import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.article.applogic.manager.network.NetworkManager;
import com.qchu.feedarticle.feature.article.applogic.manager.repository.memory.MemoryArticleRepositoryManager;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteInteractor;
import com.qchu.feedarticle.feature.favoritelistarticle.ui.presenter.FavoriteListArticlePresenter;
import com.qchu.feedarticle.feature.favoritelistarticle.ui.presenter.FavoriteListArticleUserInterface;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleWireframeInterface;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class FeedArticleConfiguration {

	static FeedArticleConfiguration sFeedArticleConfiguration;

	final ArticleInteractor articleInteractor;
	final FavoriteInteractor favoriteInteractor;

	public static FeedArticleConfiguration get() {
		if(sFeedArticleConfiguration == null) {
			sFeedArticleConfiguration = new FeedArticleConfiguration();
		}
		return sFeedArticleConfiguration;
	}

	FeedArticleConfiguration(){

		MemoryArticleRepositoryManager memoryArticleRepositoryManager =
			new MemoryArticleRepositoryManager();

		this.articleInteractor = new ArticleInteractor(new NetworkManager(){

			@Override
			public Scheduler observingScheduler() {
				return AndroidSchedulers.mainThread();
			}
		}, memoryArticleRepositoryManager);

		this.favoriteInteractor = FavoriteInteractor.create(memoryArticleRepositoryManager);
	}

	public ArticleInteractor getArticleInteractor(){
		return this.articleInteractor;
	}

	public FavoriteInteractor getFavoriteInteractor() {
		return favoriteInteractor;
	}

	public FavoriteListArticlePresenter createFavoriteListArticlePresenter(
		FavoriteListArticleUserInterface favoriteListArticleUserInterface,
	  ListArticleWireframeInterface listArticleWireframeInterface){

		return FavoriteListArticlePresenter.create(
			favoriteListArticleUserInterface,
			listArticleWireframeInterface,
			this.favoriteInteractor);
	}
}
