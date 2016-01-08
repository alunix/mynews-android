package com.qchu.mynews.applogic.load;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.applogic.load.usecase.LoadService;
import com.qchu.mynews.applogic.load.usecase.OnLoadListener;
import com.qchu.rss.RssApi;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 07/01/16.
 */
@Singleton
public class RssLoadService implements LoadService {
  private final RssApi loadService;

  @Inject
  public RssLoadService(RssApi loadService){
    this.loadService = loadService;
  }

  @Override
  public void load(String rssUrl, final OnLoadListener onLoadListener) {
    if(rssUrl == null || onLoadListener == null) return;

    loadService.load(rssUrl,
      new RssApi.OnLoadListener() {

        @Override
        public void onStarted(String rssUrl) {
          onLoadListener.onStarted();
        }

        @Override
        public void onNext(String rssUrl, com.qchu.rss.entity.Channel channel) {
          onLoadListener.onNext(rssUrl, feedFrom(channel));
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

  private Feed feedFrom(com.qchu.rss.entity.Channel rssChannel){
    if(rssChannel == null) return null;

    final Channel channel = Channel.builder()
      .title(rssChannel.title())
      .rssUrl(rssChannel.rssUrl())
      .build();

    List<Article> articles = Lists.newArrayList(Collections2.transform(rssChannel.articles(),
      new Function<com.qchu.rss.entity.Article, Article>() {
        @Nullable @Override
        public Article apply(com.qchu.rss.entity.Article input) {
          return articleFrom(input, channel);
        }
      }));

    return Feed.builder()
      .rssUrl(rssChannel.rssUrl())
      .channel(channel)
      .articles(articles)
      .feedAtDate(new Date())
      .build();
  }

  private Article articleFrom (com.qchu.rss.entity.Article rssArticle, Channel channel){
    if(rssArticle == null) return null;

    return Article.builder()
      .title(rssArticle.title())
      .link(rssArticle.link())
      .contentSnippet(rssArticle.summary())
      .content(rssArticle.content())
      .publishedDate(rssArticle.publicationDate())
      .channel(channel)
      .build();
  }
}
