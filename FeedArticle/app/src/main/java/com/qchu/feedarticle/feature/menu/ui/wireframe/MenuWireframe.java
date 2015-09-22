package com.qchu.feedarticle.feature.menu.ui.wireframe;

import android.app.Activity;

import com.qchu.feedarticle.common.Wireframe;
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuPresenter;
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuWireframeInterface;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MenuWireframe extends Wireframe implements MenuWireframeInterface{

	protected MenuWireframe(Activity fromActivity) {
		super(fromActivity);
	}

	@Override
	public void presentHomeUserInterface(MenuPresenter menuPresenter) {

	}

	@Override
	public void presentSearchUserInterface(MenuPresenter menuPresenter) {

	}
}
