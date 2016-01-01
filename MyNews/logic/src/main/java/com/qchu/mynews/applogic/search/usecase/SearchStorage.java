package com.qchu.mynews.applogic.search.usecase;

import com.qchu.mynews.applogic.search.entity.Result;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface SearchStorage {
  void save (Result result);
  Result load (String keyword);
}
