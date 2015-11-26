package com.qchu.feedarticle.ui.common;

import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.AppComponent;
import com.qchu.feedarticle.FeedArticleApplication;
import com.qchu.feedarticle.dagger.AppLogicComponent;

/**
 * Created by quocdungchu on 18/11/15.
 */
public class BaseActivity extends AppCompatActivity {

  private ActivityComponent activityComponent;

  public AppComponent appComponent(){
    return ((FeedArticleApplication)getApplication()).appComponent();
  }

  public ActivityComponent activityComponent(){
    if(activityComponent == null){
      activityComponent = DaggerActivityComponent.builder()
        .activityModule(new ActivityModule(this))
        .build();
    }
    return activityComponent;
  }
}
