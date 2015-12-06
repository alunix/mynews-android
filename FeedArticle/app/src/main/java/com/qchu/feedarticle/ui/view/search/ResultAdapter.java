package com.qchu.feedarticle.ui.view.search;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.applogic.domain.search.entity.Entry;
import com.qchu.feedarticle.ui.common.ViewHolder;
import com.qchu.feedarticle.ui.view.search.databinding.ResultItemDataBinding;

import java.util.List;

/**
 * Created by quocdungchu on 23/11/15.
 */
public class ResultAdapter extends RecyclerView.Adapter<ViewHolder<ResultItemDataBinding>> {

  private final List<Entry> entries;
  private final OnItemSelect onItemSelect;

  public ResultAdapter(List<Entry> entries, OnItemSelect onItemSelect) {
    this.entries = entries;
    this.onItemSelect = onItemSelect;
  }

  public void reload(List<Entry> entries){
    this.entries.clear();
    this.entries.addAll(entries);
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder<ResultItemDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    ResultItemDataBinding dataBinding = ResultItemDataBinding.inflate(
      LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder<>(dataBinding);
  }

  @Override
  public void onBindViewHolder(ViewHolder<ResultItemDataBinding> holder, final int position) {
    final Entry entry = entries.get(position);
    holder.dataBinding.title.setText(Html.fromHtml(entry.title()));
    holder.dataBinding.description.setText(Html.fromHtml(entry.contentSnippet()));
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(onItemSelect != null){
          onItemSelect.onSelect(entry, position);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return entries.size();
  }

  public interface OnItemSelect {
    void onSelect(Entry entry, int position);
  }
}
