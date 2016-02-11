package com.qchu.mynews.ui.common;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.qchu.mynews.common.MNApplication;
import com.qchu.mynews.common.dagger.AppComponent;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public class BaseActivity extends AppCompatActivity {

  private ActivityComponent activityComponent;

  public AppComponent appComponent(){
    return ((MNApplication)getApplication()).appComponent();
  }


  public ActivityComponent activityComponent(){
    if(activityComponent == null){
      activityComponent = DaggerActivityComponent.builder()
        .activityModule(new ActivityModule(this))
        .build();
    }
    return activityComponent;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
