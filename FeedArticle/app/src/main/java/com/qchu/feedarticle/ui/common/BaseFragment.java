package com.qchu.feedarticle.ui.common;

import android.support.v4.app.Fragment;

import com.qchu.feedarticle.FeedArticleApplication;
import com.qchu.feedarticle.dagger.AppLogicComponent;

/**
 * Created by louischu on 18/11/15.
 */
public class BaseFragment extends Fragment {

  public AppLogicComponent appLogicComponent(){
    return ((BaseActivity)getActivity()).appLogicComponent();
  }

  public ActivityComponent activityComponent(){
    return ((BaseActivity)getActivity()).activityComponent();
  }
}
