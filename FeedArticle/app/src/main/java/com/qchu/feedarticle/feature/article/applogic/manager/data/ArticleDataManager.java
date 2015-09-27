package com.qchu.feedarticle.feature.article.applogic.manager.data;

import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig;
import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.article.applogic.manager.data.rss.RSSFeed;
import com.qchu.feedarticle.feature.article.applogic.manager.data.rss.parser.xml.ParsedRSS;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by quocdungchu on 07/09/15.
 */
public abstract class ArticleDataManager implements ArticleInteractor.DataAdapter{

	public void getArticles(List<SiteConfig> siteConfigList,
	                        final ArticleInteractor.DataAdapter.GetArticleListListener
		                        getArticleListListener){

		getArticleListListener.onBegin(this);
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
			.observeOn(observingScheduler())
			.subscribe(new Subscriber<Site>() {

				@Override
				public void onError(Throwable e) {
				}

				@Override
				public void onNext(Site site) {
					getArticleListListener.onNext(ArticleDataManager.this, site.getSiteConfig(), site);
				}

				@Override
				public void onCompleted() {
					getArticleListListener.onComplete(ArticleDataManager.this);
				}
			});
	}

	public abstract Scheduler observingScheduler();

}
