package com.qchu.feedarticle.ui.common;

import android.support.v4.app.Fragment;

import com.qchu.feedarticle.AppComponent;
import com.qchu.feedarticle.FeedArticleApplication;
import com.qchu.feedarticle.dagger.AppLogicComponent;

/**
 * Created by louischu on 18/11/15.
 */
public class BaseFragment extends Fragment {

  public AppComponent appComponent(){
    return ((BaseActivity)getActivity()).appComponent();
  }

  public ActivityComponent activityComponent(){
    return ((BaseActivity)getActivity()).activityComponent();
  }
}
