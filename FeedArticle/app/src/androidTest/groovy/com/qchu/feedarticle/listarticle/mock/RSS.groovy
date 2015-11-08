package com.qchu.feedarticle.listarticle.mock

import com.qchu.feedarticle.domain.article.entity.ChannelConfig

import com.qchu.feedarticle.service.rss.RSSFeed
import com.qchu.feedarticle.service.rss.parser.html.HtmlParser
import com.qchu.feedarticle.service.rss.parser.html.ParsedImage
import com.qchu.feedarticle.service.rss.parser.xml.ParsedItem
import com.qchu.feedarticle.service.rss.parser.xml.ParsedRSS
import rx.Observable
import rx.Subscriber;


ChannelConfig siteConfig = ChannelConfig.builder()
.url("http://thethaovanhoa.vn/trang-chu.rss")
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

		println("onError "+e.getLocalizedMessage() +" "+ Thread.currentThread())

	}

	@Override
	void onNext(ParsedRSS parsedRSS) {
		println("onNextSite " + Thread.currentThread())

		println("rss " + parsedRSS)

		for(ParsedItem parsedItem:parsedRSS.getChannel().getItems()) {
				List<ParsedImage> parsedImageList = HtmlParser.getImagesFromHtml(parsedItem.getContent() != null ?
					parsedItem.getContent(): parsedItem.getDescription());
				println(parsedImageList.toString())
		}

		println("finish on next ")

	}
})
