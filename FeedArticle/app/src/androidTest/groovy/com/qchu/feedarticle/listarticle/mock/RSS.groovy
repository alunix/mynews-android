package com.qchu.feedarticle.listarticle.mock

import com.qchu.feedarticle.feature.listarticle.applogic.entity.SiteConfig

import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.RSSFeed
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.html.HtmlParser
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.html.ParsedImage
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.xml.ParsedItem
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.xml.ParsedRSS
import rx.Observable
import rx.Subscriber;


SiteConfig siteConfig = SiteConfig.builder()
.url("http://feeds.feedburner.com/CocoaDevBlog")
.build();


Observable<ParsedRSS> parsedRSSObservable =
	RSSFeed.rssObservable(siteConfig);

println("start "+ Thread.currentThread())
parsedRSSObservable.subscribe(new Subscriber<ParsedRSS>() {
	@Override
	void onCompleted() {
		println("onCompleted "+ Thread.currentThread())

	}

	@Override
	void onError(Throwable e) {
		println("onError "+ Thread.currentThread())

	}

	@Override
	void onNext(ParsedRSS parsedRSS) {
		println("onNextSite " + Thread.currentThread())

		println("rss " + parsedRSS)

		for(ParsedItem parsedItem:parsedRSS.getChannel().getItems()) {
			List<ParsedImage> parsedImageList = HtmlParser.getImagesFromHtml(parsedItem.getContent());
			println(parsedImageList.toString());
		}
	}
})
