package com.qchu.feedarticle.ui.common;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by louischu on 18/11/15.
 */
public class ViewHolder<DataBinding extends ViewDataBinding> extends RecyclerView.ViewHolder {

  public final DataBinding dataBinding;

  public ViewHolder(DataBinding dataBinding) {
    super(dataBinding.getRoot());
    this.dataBinding = dataBinding;
  }
}
