package com.qchu.rss;

import com.qchu.rss.parsed.xml.ParsedRSS;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Quoc Dung Chu on 09/01/16.
 */
public interface LoadService {
  @GET("{path}")
  Observable<ParsedRSS> load(@Path("path") String path);
}
