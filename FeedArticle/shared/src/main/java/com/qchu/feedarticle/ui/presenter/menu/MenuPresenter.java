package com.qchu.feedarticle.ui.presenter.menu;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MenuPresenter
	implements com.qchu.feedarticle.ui.presenter.common.Presenter,MenuUserInterfaceEventHandler {


	com.qchu.feedarticle.ui.presenter.menu.MenuUserInterface menuUserInterface;
	com.qchu.feedarticle.ui.presenter.menu.MenuWireframeInterface menuWireframeInterface;

	public MenuPresenter(
		com.qchu.feedarticle.ui.presenter.menu.MenuUserInterface menuUserInterface,
		com.qchu.feedarticle.ui.presenter.menu.MenuWireframeInterface menuWireframeInterface){

		this.menuUserInterface = menuUserInterface;
		this.menuWireframeInterface = menuWireframeInterface;
	}

	@Override
	public void onCreate() {
		menuWireframeInterface.presentUserInterfaceForMenuItem(com.qchu.feedarticle.ui.presenter.menu.MenuWireframeInterface.MenuItem.HOME);
	}

	@Override
	public void onDestroy() {

	}

	@Override
	public void onClickMenuItem(com.qchu.feedarticle.ui.presenter.menu.MenuWireframeInterface.MenuItem menuItem) {
		menuWireframeInterface.presentUserInterfaceForMenuItem(menuItem);
	}
}
