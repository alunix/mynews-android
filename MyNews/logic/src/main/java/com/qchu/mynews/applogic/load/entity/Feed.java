package com.qchu.mynews.applogic.load.entity;

import com.google.auto.value.AutoValue;
import com.qchu.mynews.applogic.common.entity.Article;
import com.qchu.mynews.applogic.common.entity.Channel;

import java.util.Date;
import java.util.List;


/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@AutoValue
public abstract class Feed {

  public static Builder builder(){
    return new AutoValue_Feed.Builder();
  }

  public abstract String rssUrl();
  public abstract Date feedAtDate();
  public abstract List<Article> articles();
  //public abstract Channel channel();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder rssUrl (String rssUrl);
    public abstract Builder feedAtDate (Date feedAtDate);
    public abstract Builder articles (List<Article> articles);
    //public abstract Builder channel (Channel channel);
    public abstract Feed build();
  }
}
