package com.qchu.feedarticle.applogic.service.rss;

import android.util.Log;

import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.article.entity.ChannelConfig;
import com.qchu.feedarticle.applogic.domain.article.interactor.LoadService;
import com.qchu.feedarticle.applogic.service.rss.parser.xml.ParsedRSS;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class NetworkManager implements LoadService {

	Scheduler observingScheduler;

	public NetworkManager(Scheduler observingScheduler) {
		this.observingScheduler = observingScheduler;
	}

	public void loadArticles(List<ChannelConfig> channelConfigs,
													 final OnLoadListener
														 onLoadListener){

		onLoadListener.onBegin(this);
		Observable.from(channelConfigs)
			.flatMap(new Func1<ChannelConfig, Observable<Channel>>() {
				@Override
				public Observable<Channel> call(final ChannelConfig channelConfig) {
					return RSSFeed.rssObservable(channelConfig)
						.flatMap(new Func1<ParsedRSS, Observable<Channel>>() {
							@Override
							public Observable<Channel> call(ParsedRSS parsedRSS) {
								return Observable.just(EntityTransformer.siteFrom(channelConfig, parsedRSS));
							}
						});
				}
			})
			.observeOn(observingScheduler)
			.subscribe(new Subscriber<Channel>() {

				@Override
				public void onError(Throwable e) {
					Log.d("onError Network", "Error " + e);

				}

				@Override
				public void onNext(Channel channel) {
					Log.d("onNext Network", "Site " + channel);
					onLoadListener.onNext(NetworkManager.this, channel.siteConfig(), channel);
				}

				@Override
				public void onCompleted() {
					Log.d("onCompleted Network", "Completed");
					onLoadListener.onComplete(NetworkManager.this);
				}
			});
	}
}
