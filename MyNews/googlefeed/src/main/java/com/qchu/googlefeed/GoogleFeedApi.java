package com.qchu.googlefeed;

import com.qchu.googlefeed.load.LoadService;
import com.qchu.googlefeed.search.SearchService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public class GoogleFeedApi {

  public static SearchService searchService(){
    return buildRetrofit().create(SearchService.class);
  }
  public static LoadService loadService(){
    return buildRetrofit().create(LoadService.class);
  }

  private static Retrofit buildRetrofit() {

    return new Retrofit.Builder()
      .baseUrl(Config.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .build();
  }
}
