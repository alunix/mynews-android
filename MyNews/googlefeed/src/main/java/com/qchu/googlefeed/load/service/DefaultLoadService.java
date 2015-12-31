package com.qchu.googlefeed.load.service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.gson.annotations.SerializedName;
import com.qchu.common.Log;
import com.qchu.googlefeed.Config;
import com.qchu.googlefeed.load.entity.Entry;
import com.qchu.googlefeed.load.entity.Feed;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import autovalue.shaded.com.google.common.common.collect.Lists;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public class DefaultLoadService implements LoadService {

  private final static String TAG = "DefaultLoadService";

  private final Scheduler observedOnScheduler;
  private final Scheduler subscribedOnScheduler;
  private final Log log;

  @Inject
  public DefaultLoadService(
    @Named("observedOn") Scheduler observedOnScheduler,
    @Named("subscribedOn") Scheduler subscribedOnScheduler,
    Log log) {

    this.observedOnScheduler = observedOnScheduler;
    this.subscribedOnScheduler = subscribedOnScheduler;
    this.log = log;
  }

  @Override
  public void load(final String rssUrl, final OnLoadListener onLoadListener) {
    log.d(TAG, "load ...");
    if(onLoadListener != null) {
      onLoadListener.onStarted(rssUrl);
    }

    ApiLoadService apiLoadService = buildRetrofit().create(ApiLoadService.class);
    apiLoadService.load(Config.VERSION, rssUrl)
      .flatMap(new Func1<ParsedRoot, Observable<Feed>>() {
        @Override
        public Observable<Feed> call(ParsedRoot parsedRoot) {
          return Observable.just(feedFrom(parsedRoot));
        }
      })
      .observeOn(observedOnScheduler)
      .subscribeOn(subscribedOnScheduler)
      .subscribe(new Subscriber<Feed>() {
        @Override
        public void onCompleted() {
          log.d(TAG, "load:onCompleted for rssUrl " + rssUrl);
          log.d(TAG, "load:onCompleted in thread " + Thread.currentThread());

          if(onLoadListener != null) {
            onLoadListener.onCompleted(rssUrl);
          }
        }

        @Override
        public void onError(Throwable e) {
          log.d(TAG, "load:onError for rssUrl " + rssUrl + ", error " + e.getLocalizedMessage());
          log.d(TAG, "load:onError in thread " + Thread.currentThread());

          if(onLoadListener != null) {
            onLoadListener.onError(rssUrl, e);
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

  private Retrofit buildRetrofit() {

    return new Retrofit.Builder()
      .baseUrl(Config.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .build();
  }

  private Feed feedFrom(ParsedRoot parsedRoot){
    log.d(TAG, "transform in thread " + Thread.currentThread());
    if(parsedRoot == null || parsedRoot.data == null ) {
      return null;
    } else {
      ParsedFeed parsedFeed = parsedRoot.data.feed;
      List<Entry> entries = Lists.newArrayList(Collections2.transform(parsedFeed.entries,
        new Function<ParsedEntry, Entry>() {
        @Override
        public Entry apply(ParsedEntry input) {
          return entryFrom(input);
        }
      }));
      return Feed.builder()
        .url(parsedFeed.feedUrl)
        .link(parsedFeed.link)
        .title(parsedFeed.title)
        .author(parsedFeed.author)
        .description(parsedFeed.description)
        .type(parsedFeed.type)
        .entries(entries)
        .build();
    }
  }

  private Entry entryFrom (ParsedEntry parsedEntry){
    if(parsedEntry == null) return null;

    Date publishedDate = null;
    try {
      publishedDate = dateFormat().parse(parsedEntry.publishedDate);
    } catch (ParseException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    return Entry.builder()
      .link(parsedEntry.link)
      .title(parsedEntry.title)
      .content(parsedEntry.content)
      .contentSnippet(parsedEntry.contentSnippet)
      .author(parsedEntry.author)
      .categories(parsedEntry.categories)
      .publishedDate(publishedDate)
      .build();
  }

  private DateFormat dateFormat(){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
    return simpleDateFormat;
  }

  private interface ApiLoadService {
    @GET("/ajax/services/feed/load")
    Observable<ParsedRoot> load(@Query("v") String version, @Query("q") String rssUrl);
  }

  private static class ParsedRoot {
    @SerializedName("responseData")
    private ParsedData data;

    @SerializedName("responseStatus")
    private int status;
  }

  private static class ParsedData {
    @SerializedName("feed")
    private ParsedFeed feed;
  }

  private static class ParsedFeed {
    @SerializedName("feedUrl")
    private String feedUrl;

    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    @SerializedName("author")
    private String author;

    @SerializedName("description")
    private String description;

    @SerializedName("type")
    private String type;

    @SerializedName("entries")
    private List<ParsedEntry> entries;
  }

  private static class ParsedEntry {
    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    @SerializedName("author")
    private String author;

    @SerializedName("contentSnippet")
    private String contentSnippet;

    @SerializedName("content")
    private String content;

    @SerializedName("publishedDate")
    private String publishedDate;

    @SerializedName("categories")
    private List<String> categories;
  }
}
