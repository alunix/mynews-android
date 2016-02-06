package com.qchu.mynews.applogic.search;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.qchu.common.Log;
import com.qchu.feedly.FeedlyApi;
import com.qchu.feedly.search.parsed.ParsedResult;
import com.qchu.feedly.search.parsed.ParsedSearchRoot;
import com.qchu.mynews.BuildConfig;
import com.qchu.mynews.applogic.Constants;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.search.entity.Result;
import com.qchu.mynews.applogic.search.usecase.OnSearchListener;
import com.qchu.mynews.applogic.search.usecase.SearchService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import autovalue.shaded.com.google.common.common.collect.Lists;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
@Singleton
public class FeedlySearchService implements SearchService {

  private static final String TAG = "FeedlySearchService";

  private final Scheduler observedOnScheduler;
  private final Scheduler subscribedOnScheduler;
  private final Log log;

  @Inject
  public FeedlySearchService(
    @Named(Constants.SCHEDULER_OBSERVED) Scheduler observedOnScheduler,
    @Named(Constants.SCHEDULER_SUBSCRIBED) Scheduler subscribedOnScheduler,
    Log log){

    this.observedOnScheduler = observedOnScheduler;
    this.subscribedOnScheduler = subscribedOnScheduler;
    this.log = log;
  }

  @Override
  public void search(final String keyword, final OnSearchListener onSearchListener) {
    log.d(TAG, "search ...");
    if(onSearchListener != null) {
      onSearchListener.onStarted();
    }

    FeedlyApi.buildRetrofit(client()).create(com.qchu.feedly.search.SearchService.class)
      .search(keyword, 100, "fr")
      .observeOn(observedOnScheduler)
      .subscribeOn(subscribedOnScheduler)
      .flatMap(new Func1<ParsedSearchRoot, Observable<Result>>() {
        @Override
        public Observable<Result> call(ParsedSearchRoot parsedSearchRoot) {
          return Observable.just(resultFrom(keyword, parsedSearchRoot));
        }
      })
      .subscribe(new Subscriber<Result>() {
        @Override
        public void onCompleted() {
          log.d(TAG, "search:onCompleted for keyword " + keyword);
          log.d(TAG, "search:onCompleted in thread " + Thread.currentThread());

          if(onSearchListener != null) {
            onSearchListener.onCompleted();
          }
        }

        @Override
        public void onError(Throwable e) {
          log.e(TAG, "search:onError for keyword " + keyword + ", error " + e.getLocalizedMessage());
          log.d(TAG, "search:onError in thread " + Thread.currentThread());

          if(onSearchListener != null) {
            onSearchListener.onError (e);
          }
        }

        @Override
        public void onNext(Result result) {
          log.d(TAG, "search:onNext for keyword " + keyword + ", result " + result);
          log.d(TAG, "search:onNext in thread " + Thread.currentThread());

          if(onSearchListener != null) {
            onSearchListener.onNext(keyword, result);
          }
        }
      });
  }

  private OkHttpClient client(){
    OkHttpClient client = new OkHttpClient();
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(BuildConfig.DEBUG ?
      HttpLoggingInterceptor.Level.BODY:
      HttpLoggingInterceptor.Level.NONE);
    client.interceptors().add(logging);
    return client;
  }

  private Result resultFrom (String keyword, ParsedSearchRoot parsedSearchRoot){
    List<Channel> channels = Lists.newArrayList(Collections2.transform(parsedSearchRoot.getResults(), new Function<ParsedResult, Channel>() {
      @Nullable @Override
      public Channel apply(ParsedResult input) {
        return channelFrom (input);
      }
    }));

    return Result.builder()
      .keyword(keyword)
      .channels(channels)
      .searchedDate(new Date())
      .build();
  }

  private Channel channelFrom (ParsedResult parsedResult){
    if(parsedResult == null) return null;

    return Channel.builder()
      .title(parsedResult.getTitle())
      .rssUrl(parsedResult.getFeedId().substring(0, 5)) //remove prefix 'feed/' to obtain the rss
      .link(parsedResult.getWebsite())
      .contentSnippet(parsedResult.getDescription())
      .build();
  }
}
