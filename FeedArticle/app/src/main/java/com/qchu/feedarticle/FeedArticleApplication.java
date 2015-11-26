package com.qchu.feedarticle;

import android.app.Application;

import com.qchu.feedarticle.dagger.AppLogicComponent;
import com.qchu.feedarticle.dagger.DaggerAppLogicComponent;
import com.qchu.feedarticle.dagger.RepositoryModule;
import com.qchu.feedarticle.applogic.service.rss.NetworkManager;
import com.qchu.feedarticle.applogic.repository.memory.MemoryArticleStorage;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class FeedArticleApplication extends Application {

	AppComponent appComponent;

	public void onCreate(){
		super.onCreate();

		MemoryArticleStorage memoryArticleRepository =
			new MemoryArticleStorage();
		this.appComponent = DaggerAppComponent.builder()
      .appModule(new AppModule(this))
			.repositoryModule(new RepositoryModule(new NetworkManager(AndroidSchedulers.mainThread()),
        memoryArticleRepository, memoryArticleRepository))
			.build();
	}

  public AppComponent appComponent() {
    return this.appComponent;
  }

}
