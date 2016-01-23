package com.qchu.rss;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.SimpleXmlConverterFactory;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public class RssLoadApi {

  public static LoadService loadService(String rssUrl){
    return buildRetrofit(rssUrl).create(LoadService.class);
  }

  private static Retrofit buildRetrofit(String rssUrl) {
    Retrofit retrofit = new Retrofit.Builder()
      .addConverterFactory(SimpleXmlConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .baseUrl(rssUrl)
      .build();
    return retrofit;
  }
}
