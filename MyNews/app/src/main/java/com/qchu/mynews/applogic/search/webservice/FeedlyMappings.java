package com.qchu.mynews.applogic.search.webservice;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.qchu.feedly.search.parsed.ParsedResult;
import com.qchu.feedly.search.parsed.ParsedSearchRoot;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.search.entity.Result;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

import autovalue.shaded.com.google.common.common.collect.Lists;

/**
 * Created by Quoc Dung Chu on 28/02/16.
 */
public class FeedlyMappings {

  public static Result resultFrom (String keyword, ParsedSearchRoot parsedSearchRoot){
    List<Channel> channels = Lists.newArrayList(Collections2.transform(parsedSearchRoot.getResults(), new Function<ParsedResult, Channel>() {
      @Nullable @Override public Channel apply(ParsedResult parsedResult) {
        return channelFrom(parsedResult);
      }
    }));

    return Result.builder()
      .keyword(keyword)
      .channels(channels)
      .searchedDate(new Date())
      .build();
  }

  private static Channel channelFrom (ParsedResult parsedResult){
    if(parsedResult == null) return null;

    return Channel.builder()
      .title(parsedResult.getTitle())
      .rssUrl(parsedResult.getRssUrl())
      .link(parsedResult.getWebsite())
      .contentSnippet(parsedResult.getDescription())
      .iconUrl(parsedResult.getIconUrl())
      .thumbnailUrl(parsedResult.getVisualUrl())
      .imageUrl(parsedResult.getCoverUrl())
      .build();
  }
}
