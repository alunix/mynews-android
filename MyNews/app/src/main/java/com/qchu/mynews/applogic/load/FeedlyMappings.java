package com.qchu.mynews.applogic.load;

import com.google.common.collect.Collections2;
import com.qchu.feedly.load.parsed.stream.ParsedItem;
import com.qchu.feedly.load.parsed.stream.ParsedLoadStreamRoot;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.common.util.Lists;

import java.util.Date;
import java.util.List;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public class FeedlyMappings {

  public static Feed feedFrom(
    String rssUrl,
    ParsedLoadStreamRoot parsedLoadStreamRoot,
    Date feedAtDate){

    if(parsedLoadStreamRoot == null) return null;




    Feed feed = Feed.builder()
      .rssUrl(rssUrl)
      .feedAtDate(feedAtDate)
      .build();

    return feed;
  }

  private Article articlesFrom(ParsedItem parsedItem){

    return Article.builder()
      .link(Lists.isNullOrEmpty(parsedItem.getAlternates()) ?
        null:
        parsedItem.getAlternates().get(0).getHref())
      .title(parsedItem.getTitle())
      .author(parsedItem.getAuthor())
      .contentSnippet(parsedItem.getSummary() == null ?
        null:
        parsedItem.getSummary().getContent())
      .content(parsedItem.getContent() == null ?
        null:
        parsedItem.getContent().getContent())
      //.publishedDate()
      .build();

  }
}
