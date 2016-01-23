package com.qchu.feedly;

import com.qchu.feedly.load.LoadService;
import com.qchu.feedly.search.SearchService;


import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class FeedlyApi {
  public static SearchService searchService(){
    return buildRetrofit().create(SearchService.class);
  }

  public static LoadService loadService(){
    return buildRetrofit().create(LoadService.class);
  }

  private static Retrofit buildRetrofit() {

    return new Retrofit.Builder()
      .baseUrl("http://cloud.feedly.com")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .build();
  }
}
