package com.qchu.feedly;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class FeedlyApi {
  public static SearchService searchService(){
    return buildRetrofit().create(SearchService.class);
  }

  private static Retrofit buildRetrofit() {

    return new Retrofit.Builder()
      .baseUrl("http://cloud.feedly.com")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .build();
  }
}
