package com.qchu.feedly;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public class DateJsonDeserializer implements JsonDeserializer<Date>{

  @Override
  public Date deserialize(
    JsonElement json,
    Type typeOfT,
    JsonDeserializationContext context)
    throws JsonParseException {

    long value = json.getAsJsonPrimitive().getAsLong();
    return new Date(value);
  }
}
