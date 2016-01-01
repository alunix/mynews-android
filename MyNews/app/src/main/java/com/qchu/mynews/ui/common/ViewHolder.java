package com.qchu.mynews.ui.common;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public class ViewHolder<DataBinding extends ViewDataBinding> extends RecyclerView.ViewHolder{
  public final DataBinding dataBinding;

  public ViewHolder(DataBinding dataBinding){
    super(dataBinding.getRoot());
    this.dataBinding = dataBinding;
  }
}
