package com.qchu.mynews.applogic.common.entity;

import com.google.auto.value.AutoValue;

import java.util.Date;
import java.util.List;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@AutoValue
public abstract class Channel {
  public static Builder builder(){
    return new AutoValue_Channel.Builder();
  }
  public abstract String title();
  public abstract String rssUrl();
  public abstract String link();
  public abstract String contentSnippet();


  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder title (String title);
    public abstract Builder rssUrl (String rssUrl);
    public abstract Builder link (String link);
    public abstract Builder contentSnippet (String contentSnippet);
    public abstract Channel build();
  }
}
