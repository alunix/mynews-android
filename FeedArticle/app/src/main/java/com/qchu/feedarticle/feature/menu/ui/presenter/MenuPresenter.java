package com.qchu.feedarticle.feature.menu.ui.presenter;

import com.qchu.feedarticle.common.Presenter;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MenuPresenter extends Presenter implements MenuUserInterfaceEventHandler {


	MenuUserInterface mMenuUserInterface;
	MenuWireframeInterface mMenuWireframeInterface;

	public static MenuPresenter create(MenuUserInterface menuUserInterface,
	                                   MenuWireframeInterface menuWireframeInterface){
		MenuPresenter menuPresenter = new MenuPresenter();
		menuPresenter.mMenuUserInterface = menuUserInterface;
		menuPresenter.mMenuWireframeInterface = menuWireframeInterface;

		menuPresenter.onCreate();

		return menuPresenter;
	}

	@Override
	protected void onCreate() {
		mMenuWireframeInterface.presentUserInterfaceForMenuItem(MenuWireframeInterface.MenuItem.HOME);
	}

	@Override
	protected void onDestroy() {

	}

	@Override
	public void onClickMenuItem(MenuWireframeInterface.MenuItem menuItem) {
		mMenuWireframeInterface.presentUserInterfaceForMenuItem(menuItem);
	}
}
