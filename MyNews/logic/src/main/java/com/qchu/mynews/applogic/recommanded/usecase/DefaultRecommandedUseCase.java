package com.qchu.mynews.applogic.recommanded.usecase;

import com.qchu.common.Log;
import com.qchu.mynews.applogic.load.usecase.LoadService;
import com.qchu.mynews.applogic.load.usecase.LoadStorage;
import com.qchu.mynews.applogic.load.usecase.LoadUseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
@Singleton
public class DefaultRecommandedUseCase implements RecommandedUseCase {

  private final List<String> recommandedRssUrls;
  private final LoadUseCase loadUseCase;
  private final Log log;

  @Inject
  public DefaultRecommandedUseCase(
    List<String> recommandedRssUrls,
    LoadUseCase loadUseCase,
    Log log) {

    this.recommandedRssUrls = recommandedRssUrls;
    this.loadUseCase = loadUseCase;
    this.log = log;
  }

  @Override
  public void synchronize() {

  }

  @Override
  public void synchronize(OnSynchronizeListener onSynchronizeListener) {

  }
}
