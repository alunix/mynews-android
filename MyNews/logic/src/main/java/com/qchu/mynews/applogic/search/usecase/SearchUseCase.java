package com.qchu.mynews.applogic.search.usecase;

import com.qchu.mynews.applogic.search.entity.Result;

import java.util.Date;
import java.util.List;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public interface SearchUseCase {
  void search (String keyword, OnSearchListener onSearchListener);
  Result resultForKeyword (String keyword);
  List<Result> resultsWithNumberOfDayAgo (int numberOfDayAgo);
}
