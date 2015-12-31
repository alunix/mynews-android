package com.qchu.googlefeed.search.service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.gson.annotations.SerializedName;
import com.qchu.common.Log;
import com.qchu.googlefeed.search.entity.Entry;

import java.util.ArrayList;
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
public class DefaultSearchService implements SearchService {

  private final static String TAG = "DefaultSearchService";

  private final Scheduler observedOnScheduler;
  private final Scheduler subscribedOnScheduler;
  private final Log log;

  @Inject
  public DefaultSearchService(
    @Named("observedOn") Scheduler observedOnScheduler,
    @Named("subscribedOn") Scheduler subscribedOnScheduler,
    Log log){

    this.observedOnScheduler = observedOnScheduler;
    this.subscribedOnScheduler = subscribedOnScheduler;
    this.log = log;
  }

  @Override
  public void search(final String keyword, OnSearchListener onSearchListener){
    log.d(TAG, "search ...");
    ApiFindService apiFindService = buildRetrofit().create(ApiFindService.class);
    apiFindService.find(keyword)
      .flatMap(new Func1<ParsedRoot, Observable<List<Entry>>>() {
        @Override
        public Observable<List<Entry>> call(ParsedRoot parsedRoot) {
          return Observable.just(from(parsedRoot));
        }
      })
      .observeOn(observedOnScheduler)
      .subscribeOn(subscribedOnScheduler)
      .subscribe(new Subscriber<List<Entry>>() {
        @Override
        public void onCompleted() {
          log.d(TAG, "search:onCompleted for keyword " + keyword);
          log.d(TAG, "search:onCompleted in thread " + Thread.currentThread());
        }

        @Override
        public void onError(Throwable e) {
          log.d(TAG, "search:onError for keyword " + keyword + ", error " + e.getLocalizedMessage());
          log.d(TAG, "search:onError in thread " + Thread.currentThread());
        }

        @Override
        public void onNext(List<Entry> entries) {
          log.d(TAG, "search:onNext for keyword " + keyword + ", entries " + entries);
          log.d(TAG, "search:onNext in thread " + Thread.currentThread());
        }
      });
  }

  private Retrofit buildRetrofit() {

    return new Retrofit.Builder()
      .baseUrl("https://ajax.googleapis.com")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .build();
  }

  private List<Entry> from(ParsedRoot parsedRoot){
    log.d(TAG, "transform in thread " + Thread.currentThread());
    if(parsedRoot == null || parsedRoot.data == null || parsedRoot.data.entryList == null) {
      return new ArrayList<>();
    } else {
      return Lists.newArrayList(Collections2.transform(parsedRoot.data.entryList,
        new Function<ParsedEntry, Entry>() {
          @Override
          public Entry apply(ParsedEntry input) {
            return Entry.builder()
              .url(input.url)
              .link(input.link)
              .title(input.title)
              .contentSnippet(input.title)
              .build();
          }
        }));
    }
  }

  private interface ApiFindService {
    @GET("/ajax/services/feed/find?v=1.0")
    Observable<ParsedRoot> find(@Query("q") String keyword);
  }

  private static class ParsedRoot {
    @SerializedName("responseData")
    private ParsedData data;

    @SerializedName("responseStatus")
    private int status;
  }

  private static class ParsedData {
    @SerializedName("query")
    private String query;

    @SerializedName("entries")
    private List<ParsedEntry> entryList;

  }

  private static class ParsedEntry {

    @SerializedName("url")
    private String url;

    @SerializedName("title")
    private String title;

    @SerializedName("contentSnippet")
    private String content;

    @SerializedName("link")
    private String link;
  }
}
