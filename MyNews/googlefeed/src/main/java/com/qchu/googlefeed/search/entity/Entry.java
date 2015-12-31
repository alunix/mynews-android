package com.qchu.googlefeed.search.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */

@AutoValue
public abstract class Entry {

  public static Builder builder(){
    return new AutoValue_Entry.Builder();
  }
  public abstract String url();
  public abstract String title();
  public abstract String contentSnippet();
  public abstract String link();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder url (String url);
    public abstract Builder title (String title);
    public abstract Builder contentSnippet (String contentSnippet);
    public abstract Builder link (String link);
    public abstract Entry build();
  }
}