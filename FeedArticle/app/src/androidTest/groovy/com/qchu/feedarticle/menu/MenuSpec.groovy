package com.qchu.feedarticle.menu

import com.andrewreitz.spock.android.AndroidSpecification
import com.andrewreitz.spock.android.UseActivity
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuPresenter
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuUserInterface
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuWireframeInterface
import spock.lang.Shared;

public class MenuSpec extends AndroidSpecification {

	def setupSpec() {
		println 'run setupSpec()'
	}

	def setup() { println 'run setup()' }

	def "Menu presenter"(){

		given:
		def menuUserInterface = Mock(MenuUserInterface)
		def menuUserWireframeInterface = Mock(MenuWireframeInterface)
		def menuPresenter = MenuPresenter.create(menuUserInterface,
			menuUserWireframeInterface)

		when:
		menuPresenter.onClickMenuItem(MenuWireframeInterface.MenuItem.HOME)
		then:
		1 * menuUserWireframeInterface.presentUserInterfaceForMenuItem(
			MenuWireframeInterface.MenuItem.HOME)
	}
}
