package com.qchu.mynews.applogic.load;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.qchu.common.Log;
import com.qchu.googlefeed.GoogleFeedApi;
import com.qchu.googlefeed.load.parsed.ParsedEntry;
import com.qchu.googlefeed.load.parsed.ParsedFeed;
import com.qchu.googlefeed.load.parsed.ParsedLoadRoot;
import com.qchu.mynews.applogic.Constants;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.applogic.load.usecase.LoadService;
import com.qchu.mynews.applogic.load.usecase.OnLoadListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class GoogleFeedLoadService implements LoadService {

  private static final String TAG = "GoogleFeedLoadService";

  private final Scheduler observedOnScheduler;
  private final Scheduler subscribedOnScheduler;
  private final Log log;
  private DateFormat dateFormat;

  @Inject
  public GoogleFeedLoadService(
    @Named(Constants.SCHEDULER_OBSERVED) Scheduler observedOnScheduler,
    @Named(Constants.SCHEDULER_SUBSCRIBED) Scheduler subscribedOnScheduler,
    Log log){

    this.observedOnScheduler = observedOnScheduler;
    this.subscribedOnScheduler = subscribedOnScheduler;
    this.log = log;
  }

  @Override
  public void load(final String rssUrl, final OnLoadListener onLoadListener) {
    if(rssUrl == null || onLoadListener == null) return;

    log.d(TAG, "load ...");
    if(onLoadListener != null) {
      onLoadListener.onStarted();
    }

    GoogleFeedApi.loadService().load(rssUrl)
      .observeOn(observedOnScheduler)
      .subscribeOn(subscribedOnScheduler)
      .flatMap(new Func1<ParsedLoadRoot, Observable<Feed>>() {
        @Override
        public Observable<Feed> call(ParsedLoadRoot parsedLoadRoot) {
          return Observable.just(feedFrom(parsedLoadRoot));
        }
      })
      .subscribe(new Subscriber<Feed>() {
        @Override
        public void onCompleted() {
          log.d(TAG, "load:onCompleted for rssUrl " + rssUrl);
          log.d(TAG, "load:onCompleted in thread " + Thread.currentThread());

          if(onLoadListener != null) {
            onLoadListener.onCompleted();
          }
        }

        @Override
        public void onError(Throwable e) {
          log.e(TAG, "load:onError for rssUrl " + rssUrl + ", error " + e.getLocalizedMessage());
          log.d(TAG, "load:onError in thread " + Thread.currentThread());

          if(onLoadListener != null) {
            onLoadListener.onError (e);
          }
        }

        @Override
        public void onNext(Feed feed) {
          log.d(TAG, "load:onNext for rssUrl " + rssUrl + ", feed " + feed);
          log.d(TAG, "load:onNext in thread " + Thread.currentThread());

          if(onLoadListener != null) {
            onLoadListener.onNext(rssUrl, feed);
          }
        }
      });
  }

  private Feed feedFrom(ParsedLoadRoot parsedLoadRoot){
    if(parsedLoadRoot == null
      || parsedLoadRoot.getData() == null
      || parsedLoadRoot.getData().getFeed() == null) {

      return null;
    }

    ParsedFeed parsedFeed = parsedLoadRoot.getData().getFeed();

    final Channel channel = Channel.builder()
      .title(parsedFeed.getTitle())
      .rssUrl(parsedFeed.getFeedUrl())
      .link(parsedFeed.getLink())
      .contentSnippet(parsedFeed.getDescription())
      .build();

    List<Article> articles = Lists.newArrayList(Collections2.transform(parsedFeed.getEntries(),
      new Function<ParsedEntry, Article>() {
        @Nullable @Override public Article apply(ParsedEntry input) {
          return articleFrom(input, channel);
        }
      }));

    return Feed.builder()
      .rssUrl(parsedFeed.getFeedUrl())
      .channel(channel)
      .articles(articles)
      .feedAtDate(new Date())
      .build();
  }

  private Article articleFrom (ParsedEntry parsedEntry, Channel channel){
    if(parsedEntry == null) return null;
    Date publishedDate = null;
    try {
      publishedDate = dateFormat().parse(parsedEntry.getPublishedDate());
    } catch (ParseException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    return Article.builder()
      .title(parsedEntry.getTitle())
      .link(parsedEntry.getLink())
      .author(parsedEntry.getAuthor())
      .contentSnippet(parsedEntry.getContentSnippet())
      .content(parsedEntry.getContent())
      .publishedDate(publishedDate)
      .channel(channel)
      .build();
  }

  private DateFormat dateFormat() {
    if (dateFormat == null) {
      dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
    }
    return dateFormat;
  }
}
