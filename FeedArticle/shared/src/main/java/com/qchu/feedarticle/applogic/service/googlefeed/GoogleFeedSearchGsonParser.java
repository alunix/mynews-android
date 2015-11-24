package com.qchu.feedarticle.applogic.service.googlefeed;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.qchu.feedarticle.applogic.domain.article.entity.Article;
import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.article.entity.ChannelConfig;
import com.qchu.feedarticle.applogic.domain.search.entity.Entry;
import com.qchu.once.shared.network.interactor.Parser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by quocdungchu on 08/11/15.
 */
public class GoogleFeedSearchGsonParser implements Parser<List<Entry>> {

  @Inject public GoogleFeedSearchGsonParser(){}

  @Override
  public List<Entry> parse(String fromString) {

    List<Entry> entries = new ArrayList<>();

    Gson gson = new Gson();
    ParsedRoot parsedRoot = gson.fromJson(fromString, ParsedRoot.class);

    if(parsedRoot != null
      && parsedRoot.status ==  200
      && parsedRoot.data != null
      && parsedRoot.data.entryList != null
      && !parsedRoot.data.entryList.isEmpty()){

      for(ParsedEntry parsedEntry: parsedRoot.data.entryList){
        entries.add(Entry.builder()
          .url(parsedEntry.url)
          .title(parsedEntry.title)
          .contentSnippet(parsedEntry.content)
          .link(parsedEntry.link)
          .build());
      }
    }

    return entries;
  }

  private static class ParsedRoot {
    @SerializedName("responseData")
    private ParsedData data;

    @SerializedName("responseStatus")
    private int status;
  }

  private static class ParsedData {
    @SerializedName("query")
    private String query;

    @SerializedName("entries")
    private List<ParsedEntry> entryList;

  }

  private static class ParsedEntry {

    @SerializedName("url")
    private String url;

    @SerializedName("title")
    private String title;

    @SerializedName("contentSnippet")
    private String content;

    @SerializedName("link")
    private String link;
  }
}
