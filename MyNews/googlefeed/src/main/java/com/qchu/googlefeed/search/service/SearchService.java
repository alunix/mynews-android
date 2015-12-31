package com.qchu.googlefeed.search.service;

import com.qchu.googlefeed.search.entity.Entry;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public interface SearchService {
  void search(String keyword, OnSearchListener onSearchListener);

  interface OnSearchListener {
    void onStarted(String keyword);
    void onNext (String keyword, List<Entry> entries);
    void onError (String keyword, Throwable error);
    void onCompleted (String keyword);
  }
}
