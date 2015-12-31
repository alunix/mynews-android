package com.qchu.mynews.ui.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.mynews.ui.common.BaseFragment;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public class SearchFragment extends BaseFragment {

  private SearchFragmentDataBinding dataBinding;

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    this.dataBinding = SearchFragmentDataBinding.inflate(inflater, container, false);
    dataBinding.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
    return dataBinding.getRoot();
  }
}
