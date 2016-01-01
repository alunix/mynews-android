package com.qchu.mynews.applogic.load;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.qchu.googlefeed.load.entity.Entry;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.applogic.load.usecase.LoadService;
import com.qchu.mynews.applogic.load.usecase.OnLoadListener;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class GoogleFeedLoadService implements LoadService {

  private final com.qchu.googlefeed.load.service.LoadService loadService;

  @Inject
  public GoogleFeedLoadService(
    com.qchu.googlefeed.load.service.LoadService loadService){

    this.loadService = loadService;
  }

  @Override
  public void load(String rssUrl, final OnLoadListener onLoadListener) {
    if(rssUrl == null || onLoadListener == null) return;

    loadService.load(rssUrl,
      new com.qchu.googlefeed.load.service.LoadService.OnLoadListener() {

        @Override
        public void onStarted(String rssUrl) {
          onLoadListener.onStarted();
        }

        @Override
        public void onNext(String rssUrl, com.qchu.googlefeed.load.entity.Feed feed) {
          onLoadListener.onNext(rssUrl, feedFrom(feed));
        }

        @Override
        public void onError(String rssUrl, Throwable error) {
          onLoadListener.onError(error);
        }

        @Override
        public void onCompleted(String rssUrl) {
          onLoadListener.onCompleted();
        }
      });
  }

  private Feed feedFrom(com.qchu.googlefeed.load.entity.Feed feed){
    if(feed == null) return null;

    final Channel channel = Channel.builder()
      .title(feed.title())
      .rssUrl(feed.url())
      .link(feed.link())
      .contentSnippet(feed.description())
      .build();

    List<Article> articles = Lists.newArrayList(Collections2.transform(feed.entries(),
      new Function<Entry, Article>() {
        @Nullable @Override public Article apply(Entry input) {
          return articleFrom(input, channel);
        }
      }));

    return Feed.builder()
      .rssUrl(feed.url())
      .channel(channel)
      .articles(articles)
      .feedAtDate(new Date())
      .build();
  }

  private Article articleFrom (Entry entry, Channel channel){
    if(entry == null) return null;

    return Article.builder()
      .title(entry.title())
      .link(entry.link())
      .author(entry.author())
      .contentSnippet(entry.contentSnippet())
      .content(entry.content())
      .publishedDate(entry.publishedDate())
      .channel(channel)
      .build();
  }
}
