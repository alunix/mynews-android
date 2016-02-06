package com.qchu.mynews.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

  public ChannelAdapter(List<Channel> channels) {
    this.channels = channels;
  }

  @Override
  public ViewHolder<ChannelItemDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    ChannelItemDataBinding dataBinding = ChannelItemDataBinding.inflate(
      LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(dataBinding);
  }

  @Override
  public void onBindViewHolder(ViewHolder<ChannelItemDataBinding> holder, int position) {
    Channel channel = channels.get(position);
    holder.dataBinding.title.setText(Html2.fromHtml(channel.title()));
    holder.dataBinding.contentSnippet.setText(Html2.fromHtml(channel.contentSnippet()));
  }

  @Override
  public int getItemCount() {
    return channels.size();
  }
}
