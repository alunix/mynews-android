package com.qchu.mynews.applogic.search.webservice;

import com.qchu.mynews.applogic.common.Priority;
import com.qchu.mynews.applogic.search.usecase.OnSearchListener;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface SearchWebService {
  void search (String keyword, Priority priority, OnSearchListener onSearchListener);
}
