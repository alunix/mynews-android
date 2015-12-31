package com.qchu.feedarticle.ui.view.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.ui.common.BaseActivity;

/**
 * Created by quocdungchu on 06/12/15.
 */
public class ChannelActivity extends BaseActivity {

  private ChannelActivityDataBinding dataBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_channel);
    setSupportActionBar(dataBinding.toolbar);

    final ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);


  }
}
