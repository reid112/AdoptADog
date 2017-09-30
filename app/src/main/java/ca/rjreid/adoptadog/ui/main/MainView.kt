package ca.rjreid.adoptadog.ui.main

import ca.rjreid.adoptadog.data.DataManager
import ca.rjreid.adoptadog.ui.base.BaseView

interface MainView : BaseView {
    fun initialize(dm: DataManager)
    fun showHome()
    fun showMyDog()
    fun showProfile()
}