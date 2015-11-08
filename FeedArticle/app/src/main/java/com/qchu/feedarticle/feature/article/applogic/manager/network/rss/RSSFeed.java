package com.qchu.feedarticle.feature.article.applogic.manager.network.rss;


import com.qchu.feedarticle.domain.article.entity.ChannelConfig;
import com.qchu.feedarticle.feature.article.applogic.manager.network.rss.parser.xml.ParsedRSS;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.SimpleXmlConverterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


/**
 * Created by quocdungchu on 07/09/15.
 */
public class RSSFeed {

	public static Observable<ParsedRSS> rssObservable(ChannelConfig channelConfig) {
		return retrofit(channelConfig.url())
			.create(RSSService.class)
			.getRssObservable("");
	}

	static Retrofit retrofit(String host) {
		Retrofit retrofit = new Retrofit.Builder()
			.addConverterFactory(SimpleXmlConverterFactory.create())
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			.baseUrl(host)
			.build();
		return retrofit;
	}

	interface RSSService {
		@GET("{path}")
		Observable<ParsedRSS> getRssObservable(@Path("path") String path);
	}
}
