package com.qchu.googlefeed.load;

import com.qchu.googlefeed.load.parsed.ParsedLoadRoot;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public interface LoadService {

  @GET("/ajax/services/feed/load?v=1.0")
  Observable<ParsedLoadRoot> load(@Query("q") String rssUrl);
}
