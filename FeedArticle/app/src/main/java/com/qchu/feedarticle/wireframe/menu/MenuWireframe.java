package com.qchu.feedarticle.wireframe.menu;

import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.common.FragmentWireframe;
import com.qchu.feedarticle.view.refreshlistarticle.RefreshListArticleFragment;
import com.qchu.feedarticle.presenter.menu.MenuWireframeInterface;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MenuWireframe extends FragmentWireframe implements MenuWireframeInterface {


	public MenuWireframe(AppCompatActivity fromActivity, int fragmentContainerId) {
		super(fromActivity, fragmentContainerId);
	}

	@Override
	public void presentUserInterfaceForMenuItem(MenuItem menuItem) {
		replaceFragment(new RefreshListArticleFragment());
	}
}