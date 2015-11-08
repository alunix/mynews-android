package com.qchu.feedarticle;

import android.app.Application;

import com.qchu.feedarticle.dagger.AppLogicComponent;
import com.qchu.feedarticle.dagger.DaggerAppLogicComponent;
import com.qchu.feedarticle.dagger.RepositoryModule;
import com.qchu.feedarticle.feature.article.applogic.manager.network.NetworkManager;
import com.qchu.feedarticle.repository.memory.MemoryArticleRepository;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class FeedArticleApplication extends Application {

	AppLogicComponent appLogicComponent;

	public void onCreate(){
		super.onCreate();

		MemoryArticleRepository memoryArticleRepository =
			new MemoryArticleRepository();
		this.appLogicComponent = DaggerAppLogicComponent.builder()
			.repositoryModule(new RepositoryModule(new NetworkManager(AndroidSchedulers.mainThread()),
				memoryArticleRepository, memoryArticleRepository))
			.build();
	}

	public AppLogicComponent appLogicComponent() {
		return this.appLogicComponent;
	}
}
