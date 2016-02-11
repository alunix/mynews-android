package com.qchu.mynews.ui.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.qchu.mynews.R;
import com.qchu.mynews.applogic.load.entity.Feed;
import com.qchu.mynews.applogic.load.usecase.OnLoadListener;
import com.qchu.mynews.ui.common.BaseActivity;

import java.net.URL;

import javax.annotation.Nullable;

import se.emilsjolander.intentbuilder.Extra;
import se.emilsjolander.intentbuilder.IntentBuilder;

/**
 * Created by Quoc Dung Chu on 07/02/16.
 */
@IntentBuilder
public class ArticleListActivity extends BaseActivity{

  @Extra @Nullable
  String urlToLoad;

  private ArticleListActivityDataBinding dataBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    ArticleListActivityIntentBuilder.inject(getIntent(), this);

    dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);
    setSupportActionBar(dataBinding.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    appComponent().loadUseCase().load(urlToLoad, new OnLoadListener() {
      @Override
      public void onStarted() {

      }

      @Override
      public void onNext(String rssUrl, Feed feed) {

      }

      @Override
      public void onError(Throwable error) {

      }

      @Override
      public void onCompleted() {

      }
    });

  }
}
