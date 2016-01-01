package com.qchu.mynews.ui.common;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Quoc Dung Chu on 18/11/15.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
