package com.qchu.mynews.applogic.load;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.qchu.feedly.load.parsed.stream.ParsedItem;
import com.qchu.feedly.load.parsed.stream.ParsedLoadStreamRoot;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.common.util.ListUtils;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public class FeedlyMappings {

  public static Feed feedFrom(
    ParsedLoadStreamRoot parsedLoadStreamRoot,
    Date feedAtDate){

    if(parsedLoadStreamRoot == null) return null;

    final String rssUrl = parsedLoadStreamRoot.getRssUrl();

    List<Article> articles = Lists.newArrayList(
      Collections2.transform(parsedLoadStreamRoot.getItems(),
        new Function<ParsedItem, Article>() {
          @Nullable
          @Override
          public Article apply(ParsedItem parsedItem) {
            return articlesFrom(rssUrl, parsedItem);
          }
        }));

    return Feed.builder()
      .rssUrl(rssUrl)
      .feedAtDate(feedAtDate)
      .articles(articles)
      .build();
  }

  private static Article articlesFrom(String rssUrl, ParsedItem parsedItem){

    return Article.builder()
      .link(ListUtils.isNullOrEmpty(parsedItem.getAlternates()) ? null:
        parsedItem.getAlternates().get(0).getHref())
      .title(parsedItem.getTitle())
      .author(parsedItem.getAuthor())
      .contentSnippet(parsedItem.getSummary() == null ? null: parsedItem.getSummary().getContent())
      .content(parsedItem.getContent() == null ? null: parsedItem.getContent().getContent())
      .publishedDate(parsedItem.getPublished())
      .channelRssUrl(rssUrl)
      .build();

  }
}
