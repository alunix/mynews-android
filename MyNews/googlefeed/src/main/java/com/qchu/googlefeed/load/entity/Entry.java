package com.qchu.googlefeed.load.entity;

import com.google.auto.value.AutoValue;

import java.util.Date;
import java.util.List;

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
  public abstract String link();
  public abstract String contentSnippet();
  public abstract String content();
  public abstract String author();
  public abstract Date publishedDate();
  public abstract List<String> categories();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder url (String url);
    public abstract Builder title (String title);
    public abstract Builder link (String link);
    public abstract Builder contentSnippet (String contentSnippet);
    public abstract Builder content (String content);
    public abstract Builder author (String author);
    public abstract Builder publishedDate (Date publishedDate);
    public abstract Builder categories (List<String> categories);
    public abstract Entry build();
  }
}
