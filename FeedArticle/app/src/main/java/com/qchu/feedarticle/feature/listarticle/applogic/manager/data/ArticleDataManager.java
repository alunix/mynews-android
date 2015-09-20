package com.qchu.feedarticle.feature.listarticle.applogic.manager.data;

import com.qchu.feedarticle.feature.listarticle.applogic.entity.Site;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.SiteConfig;
import com.qchu.feedarticle.feature.listarticle.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.RSSFeed;
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.xml.ParsedRSS;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ArticleDataManager implements ArticleInteractor.DataAdapter{

	public void getArticles(List<SiteConfig> siteConfigList,
	                        final ArticleInteractor.GetArticleListCallback getArticleListCallback){

		getArticleListCallback.onBegin();
		Observable.from(siteConfigList)
			.flatMap(new Func1<SiteConfig, Observable<Site>>() {
				@Override
				public Observable<Site> call(final SiteConfig siteConfig) {
					return RSSFeed.rssObservable(siteConfig)
						.flatMap(new Func1<ParsedRSS, Observable<Site>>() {
							@Override
							public Observable<Site> call(ParsedRSS parsedRSS) {
								return Observable.just(EntityTransformer.siteFrom(siteConfig, parsedRSS));
							}
						});
				}
			})
			.subscribe(new Subscriber<Site>() {
				@Override
				public void onCompleted() {
					getArticleListCallback.onFinish();
				}

				@Override
				public void onError(Throwable e) {

				}

				@Override
				public void onNext(Site site) {
					getArticleListCallback.onNext(site.getSiteConfig(), site.getArticleList(), site);
				}
			});
	}

}
