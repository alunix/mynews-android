package com.qchu.mynews.applogic.search;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.qchu.googlefeed.search.entity.Entry;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.search.entity.Result;
import com.qchu.mynews.applogic.search.usecase.OnSearchListener;
import com.qchu.mynews.applogic.search.usecase.SearchService;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import autovalue.shaded.com.google.common.common.collect.Lists;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class GoogleFeedSearchService implements SearchService {

  private final com.qchu.googlefeed.search.service.SearchService searchService;

  @Inject
  public GoogleFeedSearchService(
    com.qchu.googlefeed.search.service.SearchService searchService){
    this.searchService = searchService;
  }

  @Override
  public void search(String keyword, final OnSearchListener onSearchListener) {
    searchService.search(keyword,
      new com.qchu.googlefeed.search.service.SearchService.OnSearchListener() {
      @Override
      public void onStarted(String keyword) {
        if(onSearchListener != null){
          onSearchListener.onStarted();
        }
      }

      @Override
      public void onNext(String keyword, List<Entry> entries) {
        if(onSearchListener != null){
          List<Channel> channels = Lists.newArrayList(Collections2.transform(entries,
            new Function<Entry, Channel>() {
              @Nullable @Override public Channel apply(Entry input) {
                return channelFrom(input);
              }
            }));
          Result result = Result.builder()
            .keyword(keyword)
            .channels(channels)
            .searchedDate(new Date())
            .build();
          onSearchListener.onNext(keyword, result);
        }
      }

      @Override
      public void onError(String keyword, Throwable error) {
        if(onSearchListener != null){
          onSearchListener.onError(error);
        }
      }

      @Override
      public void onCompleted(String keyword) {
        if(onSearchListener != null){
          onSearchListener.onCompleted();
        }
      }
    });
  }

  private Channel channelFrom (Entry entry){
    if(entry == null) return null;

    return Channel.builder()
      .title(entry.title())
      .rssUrl(entry.url())
      .link(entry.link())
      .contentSnippet(entry.contentSnippet())
      .build();
  }
}
