package com.qchu.mynews.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.mynews.applogic.common.entity.Channel;
import com.qchu.mynews.ui.common.Html2;
import com.qchu.mynews.ui.common.ViewHolder;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public class ChannelAdapter extends RecyclerView.Adapter<ViewHolder<ChannelItemDataBinding>> {
  private final List<Channel> channels;
  private final OnItemClickListener onItemClickListener;

  public ChannelAdapter(List<Channel> channels, OnItemClickListener onItemClickListener) {
    this.channels = channels;
    this.onItemClickListener = onItemClickListener;
  }

  @Override
  public ViewHolder<ChannelItemDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    ChannelItemDataBinding dataBinding = ChannelItemDataBinding.inflate(
      LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(dataBinding);
  }

  @Override
  public void onBindViewHolder(ViewHolder<ChannelItemDataBinding> holder, final int position) {
    final Channel channel = channels.get(position);
    holder.dataBinding.title.setText(Html2.fromHtml(channel.title()));
    holder.dataBinding.contentSnippet.setText(Html2.fromHtml(channel.contentSnippet()));
    holder.dataBinding.setImageUrl(channel.imageUrl());
    if(onItemClickListener != null){
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          onItemClickListener.onClick(channel, position);
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return channels.size();
  }

  public interface OnItemClickListener {
    void onClick(Channel channel, int position);
  }
}
