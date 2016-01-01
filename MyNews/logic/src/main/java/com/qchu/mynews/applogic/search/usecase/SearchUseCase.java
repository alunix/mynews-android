package com.qchu.mynews.applogic.search.usecase;

import com.qchu.mynews.applogic.search.entity.Result;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public interface SearchUseCase {
  void search (String keyword, OnSearchListener onSearchListener);
  Result resultForKeyword (String keyword);
}
