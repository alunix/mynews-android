package com.qchu.feedarticle;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.article.applogic.interactor.RepositoryAdapter;
import com.qchu.feedarticle.feature.article.applogic.manager.network.NetworkManager;
import com.qchu.feedarticle.feature.article.applogic.manager.repository.memory.MemoryRepositoryManager;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteInteractor;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class FeedArticleConfiguration {

	static FeedArticleConfiguration sFeedArticleConfiguration;

	ArticleInteractor articleInteractor;
	private FavoriteInteractor favoriteInteractor;

	public static FeedArticleConfiguration get() {
		if(sFeedArticleConfiguration == null) {
			sFeedArticleConfiguration = new FeedArticleConfiguration();
		}
		return sFeedArticleConfiguration;
	}

	FeedArticleConfiguration(){

		MemoryRepositoryManager memoryRepositoryManager = new MemoryRepositoryManager();

		articleInteractor = new ArticleInteractor(new NetworkManager(){

			@Override
			public Scheduler observingScheduler() {
				return AndroidSchedulers.mainThread();
			}
		}, memoryRepositoryManager);

		favoriteInteractor = FavoriteInteractor.create(memoryRepositoryManager);
	}

	public ArticleInteractor getArticleInteractor(){
		return this.articleInteractor;
	}

	public FavoriteInteractor getFavoriteInteractor() {
		return favoriteInteractor;
	}
}
