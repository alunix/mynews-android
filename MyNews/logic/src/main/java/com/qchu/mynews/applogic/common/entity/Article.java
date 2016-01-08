package com.qchu.mynews.applogic.common.entity;

import com.google.auto.value.AutoValue;
import com.qchu.mynews.applogic.common.entity.Channel;

import java.util.Date;

import javax.annotation.Nullable;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@AutoValue
public abstract class Article {
  public static Builder builder(){
    return new AutoValue_Article.Builder();
  }
  @Nullable public abstract String title();
  @Nullable public abstract String link();
  @Nullable public abstract String author();
  @Nullable public abstract String contentSnippet();
  @Nullable public abstract String content();
  @Nullable public abstract Date publishedDate();
  public abstract Channel channel();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder title (String title);
    public abstract Builder link (String link);
    public abstract Builder author (String author);
    public abstract Builder contentSnippet (String contentSnippet);
    public abstract Builder content (String content);
    public abstract Builder publishedDate (Date publishedDate);
    public abstract Builder channel (Channel channel);
    public abstract Article build();
  }
}
