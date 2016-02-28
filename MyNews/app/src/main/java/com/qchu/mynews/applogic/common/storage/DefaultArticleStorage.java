package com.qchu.mynews.applogic.common.storage;

import com.qchu.common.utils.Log;
import com.qchu.mynews.applogic.Constants;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.database.OrmliteHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import me.ronshapiro.rx.priority.PriorityScheduler;
import rx.Scheduler;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public class DefaultArticleStorage extends BaseStorage implements ArticleStorage{

  private final PriorityScheduler databaseScheduler;
  private final Scheduler mainThreadScheduler;

  @Inject
  public DefaultArticleStorage(
    OrmliteHelper ormliteHelper,
    Log log,
    @Named(Constants.SCHEDULER_DATABASE) PriorityScheduler databaseScheduler,
    @Named(Constants.SCHEDULER_MAIN_THREAD) Scheduler mainThreadScheduler) {

    super(ormliteHelper, log);

    this.databaseScheduler = databaseScheduler;
    this.mainThreadScheduler = mainThreadScheduler;
  }

  @Override
  public void save(List<Article> articles, OnSaveListener<Article> onSaveListener) {

  }
}
