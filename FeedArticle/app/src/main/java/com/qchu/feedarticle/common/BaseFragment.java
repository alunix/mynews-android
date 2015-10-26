package com.qchu.feedarticle.common;

import android.support.v4.app.Fragment;

import com.qchu.feedarticle.FeedArticleApplication;
import com.qchu.feedarticle.dagger.AppLogicComponent;

/**
 * Created by quocdungchu on 26/10/15.
 */
public class BaseFragment extends Fragment {
	protected AppLogicComponent appLogicComponent(){
		return ((FeedArticleApplication)getActivity().getApplication())
			.appLogicComponent();
	}
}
