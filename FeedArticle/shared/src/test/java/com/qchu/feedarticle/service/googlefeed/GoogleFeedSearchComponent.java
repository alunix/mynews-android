package com.qchu.feedarticle.service.googlefeed;

import com.qchu.feedarticle.dagger.NetworkModule;
import com.qchu.feedarticle.dagger.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quocdungchu on 08/11/15.
 */
@Singleton
@Component(
	modules = {
		NetworkModule.class,
		ServiceModule.class,
		MockModule.class
	})
interface GoogleFeedSearchComponent {
	//void inject(GoogleFeedSearchServiceSpec googleFeedSearchServiceSpec);
	//GoogleFeedSearchService googleFeedSearchService();
	GoogleFeedSearchGsonParser googleFeedSearchGsonParser();
}
