package com.qchu.feedly.search;

import com.qchu.feedly.search.parsed.ParsedSearchRoot;


import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public interface SearchService {

  @GET("/v3/search/feeds")
  Observable<ParsedSearchRoot> search(
    @Query("query") String query,
    @Query("count") int count,
    @Query("locale") String locale);
}
