package com.qchu.mynews.applogic.load;

import com.google.common.base.Strings;
import com.qchu.common.Log;
import com.qchu.feedly.Converter;
import com.qchu.feedly.FeedlyApi;
import com.qchu.feedly.load.parsed.stream.ParsedLoadStreamRoot;
import com.qchu.mynews.BuildConfig;
import com.qchu.mynews.applogic.Constants;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.applogic.load.usecase.LoadService;
import com.qchu.mynews.applogic.load.usecase.OnLoadListener;
import com.qchu.mynews.common.util.ListUtils;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public class FeedlyLoadService implements LoadService {
  private static final String TAG = "FeedlyLoadService";

  private final Scheduler networkScheduler;
  private final Scheduler mainThreadScheduler;
  private final Log log;

  @Inject
  public FeedlyLoadService(
    @Named(Constants.SCHEDULER_NETWORK) Scheduler networkScheduler,
    @Named(Constants.SCHEDULER_MAIN_THREAD) Scheduler mainThreadScheduler,
    Log log){

    this.networkScheduler = networkScheduler;
    this.mainThreadScheduler = mainThreadScheduler;
    this.log = log;
  }

  @Override
  public void load(String rssUrl, final OnLoadListener onLoadListener) {
    if(Strings.isNullOrEmpty(rssUrl) || onLoadListener == null) return;

    onLoadListener.onStarted();

    downloadingObservable(rssUrl)
      .observeOn(networkScheduler)
      .subscribeOn(mainThreadScheduler)
      .flatMap(mappingFunc())
      .subscribe(new Subscriber<Feed>() {
        @Override
        public void onCompleted() {
          onLoadListener.onCompleted();
        }

        @Override
        public void onError(Throwable error) {
          onLoadListener.onError(error);
        }

        @Override
        public void onNext(Feed feed) {
          onLoadListener.onNext(feed.rssUrl(), feed);
        }
      });
  }

  @Override
  public void load(List<String> rssUrls, final OnLoadListener onLoadListener) {
    if(ListUtils.isNullOrEmpty(rssUrls) || onLoadListener == null) return;

    onLoadListener.onStarted();
    Observable.from(rssUrls)
      .observeOn(networkScheduler)
      .subscribeOn(mainThreadScheduler)
      .flatMap(downloadingFunc())
      .flatMap(mappingFunc())
      .subscribe(new Subscriber<Feed>() {
        @Override
        public void onCompleted() {
          onLoadListener.onCompleted();
        }

        @Override
        public void onError(Throwable error) {
          onLoadListener.onError(error);
        }

        @Override
        public void onNext(Feed feed) {
          onLoadListener.onNext(feed.rssUrl(), feed);
        }
      });
  }

  private Func1<String, Observable<ParsedLoadStreamRoot>> downloadingFunc() {
    return new Func1<String, Observable<ParsedLoadStreamRoot>>() {
      @Override
      public Observable<ParsedLoadStreamRoot> call(String rssUrl) {
        return downloadingObservable(rssUrl);
      }
    };
  }

  private Observable<ParsedLoadStreamRoot> downloadingObservable(String rssUrl){
    return FeedlyApi.buildRetrofit(client())
      .create(com.qchu.feedly.load.LoadService.class)
      .loadStream(Converter.feedIdFrom(rssUrl));
  }

  private Func1<ParsedLoadStreamRoot, Observable<Feed>> mappingFunc() {
    return new Func1<ParsedLoadStreamRoot, Observable<Feed>>() {
      @Override
      public Observable<Feed> call(ParsedLoadStreamRoot parsedLoadStreamRoot) {
        return Observable.just(FeedlyMappings.feedFrom(parsedLoadStreamRoot, new Date()));
      }
    };
  }

  private OkHttpClient client(){
    OkHttpClient client = new OkHttpClient();
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(BuildConfig.DEBUG ?
      HttpLoggingInterceptor.Level.BODY :
      HttpLoggingInterceptor.Level.NONE);
    client.interceptors().add(logging);
    return client;
  }
}
