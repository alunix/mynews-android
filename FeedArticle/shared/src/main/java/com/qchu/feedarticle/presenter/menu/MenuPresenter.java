package com.qchu.feedarticle.presenter.menu;

import com.qchu.feedarticle.presenter.common.Presenter;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MenuPresenter
	implements Presenter,MenuUserInterfaceEventHandler {


	MenuUserInterface menuUserInterface;
	MenuWireframeInterface menuWireframeInterface;

	public MenuPresenter(
		MenuUserInterface menuUserInterface,
		MenuWireframeInterface menuWireframeInterface){

		this.menuUserInterface = menuUserInterface;
		this.menuWireframeInterface = menuWireframeInterface;
	}

	@Override
	public void onCreate() {
		menuWireframeInterface.presentUserInterfaceForMenuItem(MenuWireframeInterface.MenuItem.HOME);
	}

	@Override
	public void onDestroy() {

	}

	@Override
	public void onClickMenuItem(MenuWireframeInterface.MenuItem menuItem) {
		menuWireframeInterface.presentUserInterfaceForMenuItem(menuItem);
	}
}
