package com.qchu.feedarticle.listarticle

import android.widget.Button
import com.andrewreitz.spock.android.AndroidSpecification

public class RSS extends AndroidSpecification{
/*
	def "download articles from rss"() {
		given:

		Observable<ParsedRSS> parsedRSSObservable =
			RSSFeed.retrofit().create(CocoaDevBlogService.class).getRssObservable();

		def subcriber = Mock(Subscriber)
		when:
		parsedRSSObservable.subscribe(subcriber)
		////subcriber.onNextSite(null)
		//callback.onNextSite(null)
		then:
		1 * subcriber.onNextSite(_)
	}
	*/
	def "I'm mocking on Android!"() {
		given:
		def mocked = Mock(Button)

		when:
		mocked.setOnClickListener(null)

		then:
		1 * mocked.setOnClickListener(null)
	}

}
