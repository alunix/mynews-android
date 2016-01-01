package com.qchu.mynews.ui.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qchu.mynews.R;
import com.qchu.mynews.applogic.search.entity.Result;
import com.qchu.mynews.ui.common.ViewHolder;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public class KeywordAdapter extends RecyclerView.Adapter<ViewHolder<KeywordItemDataBinding>> {

  private final List<Result> results;

  public KeywordAdapter(List<Result> results) {
    this.results = results;
  }

  @Override
  public ViewHolder<KeywordItemDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    KeywordItemDataBinding dataBinding = KeywordItemDataBinding.inflate(
      LayoutInflater.from(parent.getContext()), parent, false);
    dataBinding.recycleView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
    return new ViewHolder(dataBinding);
  }

  @Override
  public void onBindViewHolder(ViewHolder<KeywordItemDataBinding> holder, int position) {
    Result result = results.get(position);
    holder.dataBinding.keyword.setText(result.keyword());
    holder.dataBinding.numberChannel.setText(
      String.format(holder.itemView.getContext().getString(
          R.string.search_format_number_of_channel),
        result.channels().size()));
  }

  @Override
  public int getItemCount() {
    return results.size();
  }
}
