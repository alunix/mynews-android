package com.qchu.mynews.applogic.search.usecase;

import com.qchu.mynews.applogic.search.entity.Result;

import java.util.Date;
import java.util.List;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public interface SearchStorage {
  void save (Result result);
  Result load (String keyword);
  List<Result> resultsBetween (Date from, Date to);
}
