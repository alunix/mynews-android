package com.qchu.feedarticle;

import android.app.Application;

import com.qchu.feedarticle.dagger.AppLogicComponent;
import com.qchu.feedarticle.dagger.DaggerAppLogicComponent;
import com.qchu.feedarticle.dagger.RepositoryModule;
import com.qchu.feedarticle.feature.article.applogic.manager.network.NetworkManager;
import com.qchu.feedarticle.feature.article.applogic.manager.repository.memory.MemoryArticleRepositoryManager;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class FeedArticleApplication extends Application {

	AppLogicComponent appLogicComponent;

	public void onCreate(){
		super.onCreate();

		MemoryArticleRepositoryManager memoryArticleRepositoryManager =
			new MemoryArticleRepositoryManager();
		this.appLogicComponent = DaggerAppLogicComponent.builder()
			.repositoryModule(new RepositoryModule(new NetworkManager(AndroidSchedulers.mainThread()),
				memoryArticleRepositoryManager, memoryArticleRepositoryManager))
			.build();
	}

	public AppLogicComponent appLogicComponent() {
		return this.appLogicComponent;
	}
}
