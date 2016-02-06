package com.qchu.feedly;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class FeedlyApi {

  private final static String BASE_URL = "http://cloud.feedly.com";

  public static Retrofit buildRetrofit(OkHttpClient client) {

    return new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .build();
  }

}
