package com.qchu.feedarticle.listarticle.mock

import com.qchu.feedarticle.feature.article.applogic.entity.Site
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig
import com.qchu.feedarticle.feature.article.applogic.manager.data.EntityTransformer
import com.qchu.feedarticle.feature.article.applogic.manager.data.rss.RSSFeed
import com.qchu.feedarticle.feature.article.applogic.manager.data.rss.parser.xml.ParsedRSS
import rx.Observable
import rx.Subscriber
import rx.functions.Func1;
/*
http://feeds.feedburner.com/RayWenderlich
@"http://feeds.feedburner.com/vmwstudios",
@"http://idtypealittlefaster.blogspot.com/feeds/posts/default",
@"http://www.71squared.com/feed/",
@"http://cocoawithlove.com/feeds/posts/default",
@"http://feeds2.feedburner.com/brandontreb",
@"http://feeds.feedburner.com/CoryWilesBlog",
@"http://geekanddad.wordpress.com/feed/",
@"http://iphonedevelopment.blogspot.com/feeds/posts/default",
@"http://karnakgames.com/wp/feed/",
@"http://kwigbo.com/rss",
@"http://shawnsbits.com/feed/",
@"http://pocketcyclone.com/feed/",
@"http://www.alexcurylo.com/blog/feed/",
@"http://feeds.feedburner.com/maniacdev",
@"http://feeds.feedburner.com/macindie",
*/

def aSiteConfigs =
	[
		SiteConfig.builder()
		  .url("http://feeds.feedburner.com/CocoaDevBlog")
		  .build(),
	  SiteConfig.builder()
		  .url("http://feeds.feedburner.com/RayWenderlich")
		  .build(),
	  SiteConfig.builder()
		  .url("http://feeds.feedburner.com/vmwstudios")
		  .build(),
		/*
	  SiteConfig.builder()
		  .url("https://developer.apple.com/swift/blog/news.rss")
		  .build(),
		  */
	];




Observable.from(aSiteConfigs)
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
	void onCompleted() {
		println("onCompleted " + Thread.currentThread())

	}

	@Override
	void onError(Throwable e) {
		println("onError "+ e.getLocalizedMessage() +" " + Thread.currentThread())
	}

	@Override
	void onNext(Site site) {
		println("onNextSite " + Thread.currentThread())
		println("rss " + site)
	}
})


