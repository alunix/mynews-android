package com.qchu.feedarticle.applogic.domain.article.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Article;
import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.article.entity.ChannelConfig;
import com.qchu.feedarticle.applogic.domain.article.interactor.comparator.Comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by quocdungchu on 07/09/15.
 */
@Singleton
public class DefaultArticleInteractor implements ArticleInteractor {

  private LoadService loadService;
  private ArticleStorage articleStorage;

  @Inject public DefaultArticleInteractor(LoadService loadService, ArticleStorage articleStorage) {

    this.loadService = loadService;
    this.articleStorage = articleStorage;
  }

  @Override
  public List<Article> articlesByChannelIds(List<String> channelIds){

    List<Article> sortedArticles = articleStorage.articlesByChannelIds(channelIds);
    Collections.sort(sortedArticles, Comparators.asArticleChronology());

    return sortedArticles;
  }

  @Override
  public List<Article> articlesByArticleIds(List<String> articleIds){

    List<Article> sortedArticles = articleStorage.articlesByArticleIds(articleIds);
    Collections.sort(sortedArticles, Comparators.asArticleChronology());

    return sortedArticles;
  }

  @Override
  public Article articlesByArticleId(String articleId) {
    return articleStorage.articleByArticleId(articleId);
  }

  @Override
  public void refreshArticles(
    List<ChannelConfig> channelConfigList,
    final OnRefreshListener onRefreshListener){

    final List<Article> allArticlesSorted = new ArrayList<>();
    loadService.loadArticles(channelConfigList, new LoadService.OnLoadListener() {
      @Override
      public void onBegin(LoadService loadService) {
        onRefreshListener.onBegin(DefaultArticleInteractor.this);
      }

      @Override
      public void onNext(LoadService loadService, ChannelConfig channelConfig, Channel channel) {

        //update repository
        articleStorage.updateChannel(channel);

        allArticlesSorted.addAll(channel.articleList());
        Collections.sort(allArticlesSorted, Comparators.asArticleChronology());
        onRefreshListener.onNextSite(
          DefaultArticleInteractor.this, channel, allArticlesSorted);
      }

      @Override
      public void onComplete(LoadService loadService) {
        Collections.sort(allArticlesSorted, Comparators.asArticleChronology());
        onRefreshListener.onComplete(
          DefaultArticleInteractor.this, allArticlesSorted);
      }
    });
  }
}
