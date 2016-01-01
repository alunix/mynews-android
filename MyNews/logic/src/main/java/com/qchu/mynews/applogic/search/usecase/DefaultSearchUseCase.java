package com.qchu.mynews.applogic.search.usecase;

import com.qchu.common.Log;
import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.applogic.search.entity.Result;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class DefaultSearchUseCase implements SearchUseCase {

  private final static String TAG = "DefaultSearchUseCase";
  private final static long DAY_TIME_IN_MILLISECOND = 24 * 60 * 60 * 1000;
  private final SearchService searchService;
  private final SearchStorage searchStorage;
  private final Log log;

  @Inject public DefaultSearchUseCase(
    SearchService searchService,
    SearchStorage searchStorage,
    Log log) {

    this.searchService = searchService;
    this.searchStorage = searchStorage;
    this.log = log;
  }

  @Override
  public void search(String keyword, final OnSearchListener onSearchListener) {
    searchService.search(keyword, new OnSearchListener() {
      @Override
      public void onStarted() {
        if(onSearchListener != null){
          onSearchListener.onStarted();
        }
      }

      @Override
      public void onNext(String keyword, Result result) {
        log.d(TAG, "search:onNext for keyword " + keyword + ", result " + result);
        log.d(TAG, "search:onNext in thread " + Thread.currentThread());

        searchStorage.save(result);
        if(onSearchListener != null){
          onSearchListener.onNext(keyword, result);
        }
      }

      @Override
      public void onError(Throwable error) {
        if(onSearchListener != null){
          onSearchListener.onError(error);
        }
      }

      @Override
      public void onCompleted() {
        if(onSearchListener != null){
          onSearchListener.onCompleted();
        }
      }
    });
  }

  @Override
  public Result resultForKeyword(String keyword) {
    return keyword != null ? searchStorage.load (keyword): null;
  }

  @Override
  public List<Result> resultsWithNumberOfDayAgo(int numberOfDayAgo) {
    Date currentDate = new Date();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDate);

    calendar.set(Calendar.MILLISECOND, 999);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.HOUR, 23);

    //today ending
    Date to = calendar.getTime();

    Date from = new Date(to.getTime() - (numberOfDayAgo + 1) * DAY_TIME_IN_MILLISECOND + 1);

    List<Result> results = searchStorage.resultsBetween (from ,to);

    Collections.sort(results, new Comparator<Result>() {
      @Override
      public int compare(Result o1, Result o2) {
        return o1.searchedDate().compareTo(o2.searchedDate());
      }
    });

    return results;
  }
}
