package com.qchu.mynews.applogic.search.entity;

import com.google.auto.value.AutoValue;
import com.qchu.mynews.applogic.common.entity.Channel;

import java.util.Date;
import java.util.List;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@AutoValue
public abstract class Result {

  public static Builder builder(){
    return new AutoValue_Result.Builder();
  }

  public abstract String keyword();
  public abstract Date searchedDate();
  public abstract List<Channel> channels();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder keyword (String keyword);
    public abstract Builder searchedDate (Date searchedDate);
    public abstract Builder channels (List<Channel> channels);
    public abstract Result build();
  }
}
