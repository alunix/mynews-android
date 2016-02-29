package com.qchu.mynews.applogic.common.storage;

import com.qchu.common.utils.Log;
import com.qchu.mynews.applogic.Constants;
import com.qchu.mynews.applogic.common.Priority;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.database.OrmliteHelper;
import com.qchu.mynews.applogic.database.model.DbArticle;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Named;

import me.ronshapiro.rx.priority.PriorityScheduler;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public class DefaultArticleStorage extends BaseStorage implements ArticleStorage{

  private static final String TAG = "DefaultArticleStorage";
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
  public void save(
    final List<Article> articles,
    final Priority priority,
    final OnSaveListener<Article> onSaveListener) {

    Observable.create(new Observable.OnSubscribe<Article>() {

      @Override
      public void call(final Subscriber<? super Article> subscriber) {

        Scheduler.Worker observeWorker = databaseScheduler.priority(priority.getValue())
          .createWorker();
        final Scheduler.Worker subscribeWorker = mainThreadScheduler.createWorker();

        subscribeWorker.schedule(new Action0() {
          @Override
          public void call() {
            subscriber.onStart();
          }
        });

        observeWorker.schedule(new Action0() {
          @Override
          public void call() {
            try {
              ormliteHelper.articleDao().callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                  log.d(TAG, "save in database: observe " + Thread.currentThread());
                  for (final Article article : articles) {
                    save(article);
                    subscribeWorker.schedule(new Action0() {
                      @Override
                      public void call() {
                        subscriber.onNext(article);
                      }
                    });
                  }
                  return null;
                }
              });
              subscribeWorker.schedule(new Action0() {
                @Override
                public void call() {
                  subscriber.onCompleted();
                }
              });
            } catch (final Exception e) {
              log.e(TAG, e.getLocalizedMessage());
              e.printStackTrace();
              subscribeWorker.schedule(new Action0() {
                @Override
                public void call() {
                  subscriber.onError(e);
                }
              });
            }
          }
        });
      }})
      .subscribe(new Subscriber<Article>() {

        @Override
        public void onStart() {
          log.d(TAG, "save in database: onStart " + Thread.currentThread());
        }

        @Override
        public void onCompleted() {
          log.d(TAG, "save in database: onCompleted " + Thread.currentThread());

        }

        @Override
        public void onError(Throwable e) {
          log.d(TAG, "save in database: onError " + e.getLocalizedMessage());
        }

        @Override
        public void onNext(Article article) {
          log.d(TAG, "save in database: onNext "+ Thread.currentThread());
        }
      });
  }

  private void save (Article article){
    log.d(TAG, "save in database: save article: "+ article.title());
  }
}
