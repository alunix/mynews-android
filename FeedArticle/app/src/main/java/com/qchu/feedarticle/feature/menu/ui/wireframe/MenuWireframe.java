package com.qchu.feedarticle.feature.menu.ui.wireframe;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.common.FragmentWireframe;
import com.qchu.feedarticle.common.Wireframe;
import com.qchu.feedarticle.feature.listarticle.ui.view.ListArticleFragment;
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuPresenter;
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuWireframeInterface;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MenuWireframe extends FragmentWireframe implements MenuWireframeInterface {


	public MenuWireframe(AppCompatActivity fromActivity, int fragmentContainerId) {
		super(fromActivity, fragmentContainerId);
	}

	@Override
	public void presentUserInterfaceForMenuItem(MenuItem menuItem) {
		replaceFragment(new ListArticleFragment());
	}
}
