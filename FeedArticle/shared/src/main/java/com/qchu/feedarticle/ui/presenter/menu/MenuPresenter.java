package com.qchu.feedarticle.ui.presenter.menu;

import com.qchu.feedarticle.ui.presenter.common.Presenter;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MenuPresenter
	implements Presenter,MenuUserInterfaceEventHandler {


	private final MenuUserInterface menuUserInterface;
	private final MenuWireframeInterface menuWireframeInterface;

	public MenuPresenter(
		MenuUserInterface menuUserInterface,
		MenuWireframeInterface menuWireframeInterface){

		this.menuUserInterface = menuUserInterface;
		this.menuWireframeInterface = menuWireframeInterface;
	}

	@Override
	public void onCreate() {
		menuWireframeInterface.presentUserInterfaceForMenuItem(
			MenuWireframeInterface.MenuItem.HOME);
	}

	@Override
	public void onDestroy() {

	}

	@Override
	public void onClickMenuItem(MenuWireframeInterface.MenuItem menuItem) {
		menuWireframeInterface.presentUserInterfaceForMenuItem(menuItem);
	}
}
