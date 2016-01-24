package com.qchu.feedly;

import com.qchu.feedly.load.LoadService;
import com.qchu.feedly.search.SearchService;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;


import java.io.IOException;

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

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient();
    client.interceptors().add(logging);

    return new Retrofit.Builder()
      .baseUrl("http://cloud.feedly.com")
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .build();
  }
}
