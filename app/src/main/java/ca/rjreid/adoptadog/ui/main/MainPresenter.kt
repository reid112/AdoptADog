package ca.rjreid.adoptadog.ui.main

import android.os.Bundle
import ca.rjreid.adoptadog.data.DataManager
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import io.reactivex.disposables.CompositeDisposable
import com.evernote.android.state.State
import com.evernote.android.state.StateSaver

class MainPresenter constructor(private var mainView: MainView, private var dataManager: DataManager) : AHBottomNavigation.OnTabSelectedListener {
    //region Variables
    private val compositeDisposable = CompositeDisposable()
    //endregion

    //region Commands
    fun create() {
        mainView.initialize()
    }

    fun saveInstanceState(outState: Bundle?) {
        outState?.let {
            StateSaver.saveInstanceState(this, it)
        }
    }

    fun restoreInstanceState(savedInstanceState: Bundle?) {
        StateSaver.restoreInstanceState(this, savedInstanceState)
    }

    fun destroy() = compositeDisposable.clear()
    //endregion

    //region Bottom Navigation Implementation
    override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
        when (position)
        {
            0 -> mainView.showHome()
            1 -> mainView.showMyDog()
            2 -> mainView.showProfile()
        }

        return true
    }
    //endregion
}