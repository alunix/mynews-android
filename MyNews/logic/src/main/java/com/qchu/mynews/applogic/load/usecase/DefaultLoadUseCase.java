package com.qchu.mynews.applogic.load.usecase;

import com.qchu.common.Connectivity;
import com.qchu.common.Log;
import com.qchu.mynews.applogic.load.entity.Feed;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class DefaultLoadUseCase implements LoadUseCase {

  private final LoadService loadService;
  private final LoadStorage loadStorage;
  private final Connectivity connectivity;
  private final Log log;

  @Inject
  public DefaultLoadUseCase(
    LoadService loadService,
    LoadStorage loadStorage,
    Connectivity connectivity,
    Log log) {

    this.loadService = loadService;
    this.loadStorage = loadStorage;
    this.connectivity = connectivity;
    this.log = log;
  }

  @Override
  public void load(final String rssUrl, final OnLoadListener onLoadListener) {

    if(connectivity.isConnected()){
      loadService.load(rssUrl, new OnLoadListener() {
        @Override
        public void onStarted() {
          if(onLoadListener != null) {
            onLoadListener.onStarted();
          }
        }

        @Override
        public void onNext(String rssUrl, Feed feed) {
          loadStorage.save(feed);
          if(onLoadListener != null) {
            onLoadListener.onNext(rssUrl, feed);
          }
        }

        @Override
        public void onError(Throwable error) {
          if(onLoadListener != null) {
            onLoadListener.onError(error);
          }
        }

        @Override
        public void onCompleted() {
          if(onLoadListener != null) {
            onLoadListener.onCompleted();
          }
        }
      });
    } else {
      if(onLoadListener != null){
        Feed feed = loadStorage.load(rssUrl);
        onLoadListener.onStarted();
        onLoadListener.onNext(rssUrl, feed);
        onLoadListener.onCompleted();
      }
    }
  }
}