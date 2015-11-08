package com.qchu.feedarticle.view.detailarticle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qchu.feedarticle.view.detailarticle.databinding.BindableArticle;

import java.util.List;

/**
 * Created by quocdungchu on 29/09/15.
 */
public class DetailArticleViewPagerAdater extends FragmentPagerAdapter {

	List<BindableArticle> mBindableArticleList;

	public DetailArticleViewPagerAdater(FragmentManager fm,
	                                    List<BindableArticle> bindableArticleList) {
		super(fm);
		mBindableArticleList = bindableArticleList;
	}

	public void reload(List<BindableArticle> bindableArticleList) {
		mBindableArticleList = bindableArticleList;
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int position) {

		return new DetailArticleFragmentBuilder(
			mBindableArticleList.get(position))
			.build();

		//return new CheesePageDetailFragment();
	}

	@Override
	public int getCount() {
		return mBindableArticleList.size();
	}
}
