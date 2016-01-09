package com.qchu.googlefeed.search;

import com.qchu.googlefeed.search.parsed.ParsedSearchRoot;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public interface SearchService {
  @GET("/ajax/services/feed/find?v=1.0")
  Observable<ParsedSearchRoot> search(@Query("q") String keyword);
}
