package com.qchu.feedarticle.dagger;

/**
 * Created by quocdungchu on 08/11/15.
 */

import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.search.entity.Entry;
import com.qchu.feedarticle.applogic.domain.search.interactor.SearchService;
import com.qchu.feedarticle.applogic.service.googlefeed.GoogleFeedSearchGsonParser;
import com.qchu.feedarticle.applogic.service.googlefeed.GoogleFeedSearchService;
import com.qchu.once.shared.network.interactor.Parser;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

	@Provides @Singleton
	SearchService provideSearchService(GoogleFeedSearchService googleFeedSearchService){
		return googleFeedSearchService;
	}

	@Provides @Singleton
	Parser<List<Entry>> provideParser(GoogleFeedSearchGsonParser googleFeedSearchGsonParser){
		return googleFeedSearchGsonParser;
	}
}
