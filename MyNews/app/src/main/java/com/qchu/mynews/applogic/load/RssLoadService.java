package com.qchu.mynews.applogic.load;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.qchu.common.Log;
import com.qchu.mynews.applogic.Constants;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.applogic.load.usecase.LoadService;
import com.qchu.mynews.applogic.load.usecase.OnLoadListener;
import com.qchu.rss.parsed.xml.ParsedChannel;
import com.qchu.rss.parsed.xml.ParsedItem;
import com.qchu.rss.parsed.xml.ParsedRSS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.SimpleXmlConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Quoc Dung Chu on 07/01/16.
 */
@Singleton
public class RssLoadService implements LoadService {

  private static final String TAG = "RssLoadService";

  private final Scheduler observedOnScheduler;
  private final Scheduler subscribedOnScheduler;
  private final Log log;
  private DateFormat dateFormat;

  @Inject
  public RssLoadService(
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

    if(onLoadListener != null) {
      onLoadListener.onStarted();
    }

    buildRetrofit(rssUrl).create(com.qchu.rss.LoadService.class)
      .load("")
      .flatMap(new Func1<ParsedRSS, Observable<Feed>>() {
        @Override
        public Observable<Feed> call(ParsedRSS parsedRSS) {
          return  Observable.just(feedFrom(rssUrl, parsedRSS.getChannel()));
        }
      })
      .subscribeOn(subscribedOnScheduler)
      .observeOn(observedOnScheduler)
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
            onLoadListener.onError(e);
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

  private Feed feedFrom(String rssUrl, ParsedChannel parsedChannel){
    if(parsedChannel == null) return null;

    final Channel channel = Channel.builder()
      .title(parsedChannel.getTitle())
      .rssUrl(rssUrl)
      .build();

    List<Article> articles = Lists.newArrayList(Collections2.transform(parsedChannel.getItems(),
      new Function<ParsedItem, Article>() {
        @Nullable @Override
        public Article apply(ParsedItem input) {
          return articleFrom(input, channel);
        }
      }));

    return Feed.builder()
      .rssUrl(rssUrl)
      .channel(channel)
      .articles(articles)
      .feedAtDate(new Date())
      .build();
  }

  private Article articleFrom (ParsedItem parsedItem, Channel channel){
    if(parsedItem == null) return null;

    Date publishedDate = null;
    try {
      publishedDate = dateFormat().parse(parsedItem.getPubDate());
    } catch (ParseException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    return Article.builder()
      .title(parsedItem.getTitle())
      .link(parsedItem.getLink())
      .contentSnippet(parsedItem.getDescription())
      .content(parsedItem.getContent())
      .publishedDate(publishedDate)
      .channel(channel)
      .build();
  }

  private static Retrofit buildRetrofit(String rssUrl) {
    Retrofit retrofit = new Retrofit.Builder()
      .addConverterFactory(SimpleXmlConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .baseUrl(rssUrl)
      .build();
    return retrofit;
  }

  private DateFormat dateFormat() {
    if (dateFormat == null) {
      dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
    }
    return dateFormat;
  }
}
