package com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss;


import com.qchu.feedarticle.feature.listarticle.applogic.entity.SiteConfig;
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.xml.ParsedRSS;

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

	public static Observable<ParsedRSS> rssObservable(SiteConfig siteConfig) {
		return retrofit(siteConfig.getUrl())
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
