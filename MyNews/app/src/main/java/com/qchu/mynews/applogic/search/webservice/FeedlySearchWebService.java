package com.qchu.mynews.applogic.search.webservice;

import com.qchu.common.utils.Log;
import com.qchu.feedly.FeedlyApi;
import com.qchu.feedly.search.SearchService;
import com.qchu.feedly.search.parsed.ParsedSearchRoot;
import com.qchu.mynews.BuildConfig;
import com.qchu.mynews.applogic.Constants;
import com.qchu.mynews.applogic.common.Priority;
import com.qchu.mynews.applogic.search.entity.Result;
import com.qchu.mynews.applogic.search.usecase.OnSearchListener;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import me.ronshapiro.rx.priority.PriorityScheduler;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
@Singleton
public class FeedlySearchWebService implements SearchWebService {

  private static final String TAG = "FeedlySearchWebService";

  private final Scheduler mainThreadScheduler;
  private final PriorityScheduler networkScheduler;
  private final Log log;

  @Inject
  public FeedlySearchWebService(
    @Named(Constants.SCHEDULER_MAIN_THREAD) Scheduler mainThreadScheduler,
    @Named(Constants.SCHEDULER_NETWORK) PriorityScheduler networkScheduler,
    Log log){

    this.mainThreadScheduler = mainThreadScheduler;
    this.networkScheduler = networkScheduler;
    this.log = log;
  }

  @Override
  public void search(final String keyword, Priority priority, final OnSearchListener onSearchListener) {
    log.d(TAG, "search ...");
    if(onSearchListener != null) {
      onSearchListener.onStarted();
    }

    FeedlyApi.buildRetrofit(client()).create(SearchService.class)
      .search(keyword, 100, "fr")
      .observeOn(mainThreadScheduler)
      .subscribeOn(networkScheduler.priority(priority.getValue()))
      .flatMap(new Func1<ParsedSearchRoot, Observable<Result>>() {
        @Override
        public Observable<Result> call(ParsedSearchRoot parsedSearchRoot) {
          return Observable.just(FeedlyMappings.resultFrom(keyword, parsedSearchRoot));
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
}
