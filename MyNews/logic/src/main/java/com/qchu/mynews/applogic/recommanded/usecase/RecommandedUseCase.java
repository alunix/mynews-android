package com.qchu.mynews.applogic.recommanded.usecase;

import com.qchu.mynews.applogic.common.Priority;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public interface RecommandedUseCase {
  void synchronize(List<String> rssUrls, OnSynchronizeListener onSynchronizeListener);
}
