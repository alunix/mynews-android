package com.qchu.googlefeed.load.entity;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
@AutoValue
public abstract class Feed {

  public abstract String url();
  public abstract String title();
  public abstract String link();
  public abstract String description();
  public abstract String author();
  public abstract String type();
  public abstract List<Entry> entries();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder url (String url);
    public abstract Builder title (String title);
    public abstract Builder link (String link);
    public abstract Builder description (String description);
    public abstract Builder author (String author);
    public abstract Builder type (String type);
    public abstract Builder entries (List<Entry> entries);
    public abstract Feed build();
  }
}
