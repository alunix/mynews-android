package com.qchu.mynews.applogic.recommanded.usecase;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public interface RecommandedUseCase {
  void synchronize(String rssUrl);
  void synchronize(List<String> rssUrls, OnSynchronizeListener onSynchronizeListener);
}
