package com.qchu.mynews.applogic.search.usecase;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface SearchService {
  void search (String keyword, OnSearchListener onSearchListener);
}
