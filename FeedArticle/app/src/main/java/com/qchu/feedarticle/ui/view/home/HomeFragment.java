package com.qchu.feedarticle.ui.view.home;

import android.os.Bundle;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.ui.common.BaseFragment;

/**
 * Created by louischu on 18/11/15.
 */
public class HomeFragment extends BaseFragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityComponent().titleController().title(getString(R.string.title_home));
  }
}
