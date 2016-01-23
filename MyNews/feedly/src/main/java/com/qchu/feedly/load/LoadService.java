package com.qchu.feedly.load;

import com.qchu.feedly.load.parsed.entry.ParsedLoadEntryRoot;
import com.qchu.feedly.load.parsed.stream.ParsedLoadStreamRoot;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */
public interface LoadService {
  @GET("/v3/streams/ids")
  Observable<ParsedLoadEntryRoot> loadEntry(
    @Query("streamId") String streamId,
    @Query("count") int count,
    @Query("ranked") String ranked,
    @Query("unreadOnly") boolean unreadOnly,
    @Query("newerThan") long newerThan,
    @Query("continuation") String continuation
  );

  @GET("/v3/streams/contents")
  Observable<ParsedLoadStreamRoot> loadStream(
    @Query("streamId") String streamId,
    @Query("count") int count,
    @Query("ranked") String ranked,
    @Query("unreadOnly") boolean unreadOnly,
    @Query("newerThan") long newerThan,
    @Query("continuation") String continuation
  );
}
