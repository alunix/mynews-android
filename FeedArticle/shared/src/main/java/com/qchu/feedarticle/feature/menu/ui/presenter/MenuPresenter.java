package com.qchu.feedarticle.feature.menu.ui.presenter;

import com.qchu.feedarticle.common.Presenter;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MenuPresenter extends Presenter implements MenuUserInterfaceEventHandler {


	MenuUserInterface menuUserInterface;
	MenuWireframeInterface menuWireframeInterface;

	public static MenuPresenter create(MenuUserInterface menuUserInterface,
	                                   MenuWireframeInterface menuWireframeInterface){
		MenuPresenter menuPresenter = new MenuPresenter();
		menuPresenter.menuUserInterface = menuUserInterface;
		menuPresenter.menuWireframeInterface = menuWireframeInterface;

		menuPresenter.onCreate();

		return menuPresenter;
	}

	@Override
	protected void onCreate() {
		menuWireframeInterface.presentUserInterfaceForMenuItem(MenuWireframeInterface.MenuItem.HOME);
	}

	@Override
	protected void onDestroy() {

	}

	@Override
	public void onClickMenuItem(MenuWireframeInterface.MenuItem menuItem) {
		menuWireframeInterface.presentUserInterfaceForMenuItem(menuItem);
	}
}
