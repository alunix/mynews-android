package com.qchu.mynews.ui.common;

import android.support.v7.app.AppCompatActivity;

import com.qchu.mynews.common.MNApplication;
import com.qchu.mynews.common.dagger.AppComponent;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public class BaseActivity extends AppCompatActivity {

  public AppComponent appComponent(){
    return ((MNApplication)getApplication()).appComponent();
  }
}
