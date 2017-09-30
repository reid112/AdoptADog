package ca.rjreid.adoptadog.ui.main

import ca.rjreid.adoptadog.ui.base.BaseView

interface MainView : BaseView {
    fun initialize()
    fun showHome()
    fun showMyDog()
    fun showMessages()
    fun showProfile()
}