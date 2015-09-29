package com.qchu.feedarticle.feature.detailarticle.ui.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by quocdungchu on 29/09/15.
 */
public class DetailArticleViewPagerAdater extends FragmentPagerAdapter {

	public DetailArticleViewPagerAdater(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return null;
	}

	@Override
	public int getCount() {
		return 0;
	}
}
