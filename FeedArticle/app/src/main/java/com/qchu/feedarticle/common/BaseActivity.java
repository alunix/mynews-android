package com.qchu.feedarticle.common;

import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.FeedArticleApplication;
import com.qchu.feedarticle.dagger.AppLogicComponent;

/**
 * Created by quocdungchu on 26/10/15.
 */
public class BaseActivity extends AppCompatActivity {
	protected AppLogicComponent appLogicComponent(){
		return ((FeedArticleApplication)getApplication()).appLogicComponent();
	}
}
