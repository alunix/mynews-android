package com.qchu.mynews.applogic.load.usecase;

import com.qchu.common.Connectivity;
import com.qchu.common.Log;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.applogic.load.webservice.LoadWebService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class DefaultLoadUseCase implements LoadUseCase {

  private final LoadWebService loadWebService;
  private final com.qchu.mynews.applogic.load.storage.LoadStorage loadStorage;
  private final Connectivity connectivity;
  private final Log log;

  @Inject
  public DefaultLoadUseCase(
    LoadWebService loadWebService,
    com.qchu.mynews.applogic.load.storage.LoadStorage loadStorage,
    Connectivity connectivity,
    Log log) {

    this.loadWebService = loadWebService;
    this.loadStorage = loadStorage;
    this.connectivity = connectivity;
    this.log = log;
  }

  @Override
  public void load(final String rssUrl, final OnLoadListener onLoadListener) {

    if(connectivity.isConnected()){
      loadWebService.load(rssUrl, new OnLoadListener() {
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
      //TODO
    }
  }

  @Override
  public void load(List<String> rssUrls, final OnLoadListener onLoadListener) {

    if(connectivity.isConnected()){
      loadWebService.load(rssUrls, new OnLoadListener() {
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
      //TODO
    }
  }
}
