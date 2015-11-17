package com.qchu.feedarticle.menu

import com.andrewreitz.spock.android.AndroidSpecification
import com.qchu.feedarticle.ui.presenter.menu.MenuPresenter
import com.qchu.feedarticle.ui.presenter.menu.MenuUserInterface
import com.qchu.feedarticle.ui.presenter.menu.MenuWireframeInterface

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
