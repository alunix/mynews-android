package com.qchu.mynews.ui.common;

import android.support.v4.app.Fragment;

import com.qchu.mynews.common.MNApplication;
import com.qchu.mynews.common.dagger.AppComponent;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public class BaseFragment extends Fragment {

  public AppComponent appComponent(){
    return ((MNApplication)getActivity().getApplication()).appComponent();
  }

  public ActivityComponent activityComponent(){
    return ((BaseActivity)getActivity()).activityComponent();
  }
}
