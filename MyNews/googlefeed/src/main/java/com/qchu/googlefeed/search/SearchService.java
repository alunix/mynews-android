package com.qchu.googlefeed.search;

import com.qchu.googlefeed.search.entity.Entry;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public class SearchService {

  public void search(String keyword, OnSearchListener onSearchListener){

  }

  public interface OnSearchListener {
    void onSearch(List<Entry> entries);
  }
}
