package com.qchu.rss.parsed.html;

import com.google.auto.value.AutoValue;

/**
 * Created by quocdungchu on 19/09/15.
 */
@AutoValue
public abstract class ParsedImage {
	public static ParsedImage of(String src, float width, float height) {
    return new AutoValue_ParsedImage(src, width, height);
  }
	public abstract String src();
	public abstract float width();
	public abstract float height();
	public boolean isValid(){
		return src() != null && !src().equals("");
	}
}
