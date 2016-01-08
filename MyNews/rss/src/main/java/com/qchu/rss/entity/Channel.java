package com.qchu.rss.entity;

import com.google.auto.value.AutoValue;

import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by Quoc Dung Chu on 02/01/16.
 */
@AutoValue
public abstract class Channel {
  public static Builder builder(){
    return new AutoValue_Channel.Builder();
  }
  public abstract String rssUrl();
  @Nullable public abstract String title();
  public abstract List<Article> articles();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder rssUrl (String rssUrl);
    public abstract Builder title (String title);
    public abstract Builder articles (List<Article> articles);
    public abstract Channel build();
  }
}
