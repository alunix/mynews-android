package com.qchu.feedarticle.applogic.domain.article.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Article;
import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.article.entity.ChannelConfig;

import java.util.ArrayList;
import java.util.Collections;
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

    List<Article> sortedArticleList = articleStorage.articlesByChannelIds(channelIds);
    Collections.sort(sortedArticleList, new ArticleChronologyComparator());

    return sortedArticleList;
  }

  @Override
  public List<Article> articlesByArticleIds(List<String> articleIdList){

    List<Article> sortedArticleList = articleStorage.articlesByArticleIds(articleIdList);
    Collections.sort(sortedArticleList, new ArticleChronologyComparator());

    return sortedArticleList;
  }

  @Override
  public Article articlesByArticleId(String articleId) {
    return articleStorage.articleByArticleId(articleId);
  }

  @Override
  public void refreshArticles(
    List<ChannelConfig> channelConfigList,
    final OnRefreshListener onRefreshListener){

    final List<Article> allArticleSortedList = new ArrayList<>();
    loadService.loadArticles(channelConfigList, new LoadService.OnLoadListener() {
      @Override
      public void onBegin(LoadService loadService) {
        onRefreshListener.onBegin(DefaultArticleInteractor.this);
      }

      @Override
      public void onNext(LoadService loadService, ChannelConfig channelConfig, Channel channel) {

        //update repository
        articleStorage.updateChannel(channel);

        allArticleSortedList.addAll(channel.articleList());
        Collections.sort(allArticleSortedList, new ArticleChronologyComparator());
        onRefreshListener.onNextSite(
          DefaultArticleInteractor.this, channel, allArticleSortedList);
      }

      @Override
      public void onComplete(LoadService loadService) {
        Collections.sort(allArticleSortedList, new ArticleChronologyComparator());
        onRefreshListener.onComplete(
          DefaultArticleInteractor.this, allArticleSortedList);
      }
    });
  }
}
