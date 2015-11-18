package com.qchu.feedarticle.ui.common;

import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.FeedArticleApplication;
import com.qchu.feedarticle.dagger.AppLogicComponent;

/**
 * Created by louischu on 18/11/15.
 */
public class BaseActivity extends AppCompatActivity {

  public AppLogicComponent appLogicComponent(){
    return ((FeedArticleApplication)getApplication()).appLogicComponent();
  }
}
