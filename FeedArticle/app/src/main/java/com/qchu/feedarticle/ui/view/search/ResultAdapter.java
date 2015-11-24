package com.qchu.feedarticle.ui.view.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qchu.feedarticle.ui.common.ViewHolder;
import com.qchu.feedarticle.ui.view.search.databinding.ResultItemDataBinding;

/**
 * Created by louischu on 23/11/15.
 */
public class ResultAdapter extends RecyclerView.Adapter<ViewHolder<ResultItemDataBinding>> {

  @Override
  public ViewHolder<ResultItemDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    ResultItemDataBinding dataBinding = ResultItemDataBinding.inflate(
      LayoutInflater.from(parent.getContext()), parent, false);

    return new ViewHolder<>(dataBinding);
  }

  @Override
  public void onBindViewHolder(ViewHolder<ResultItemDataBinding> holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }
}
