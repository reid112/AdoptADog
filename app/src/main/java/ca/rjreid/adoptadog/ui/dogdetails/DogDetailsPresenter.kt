package ca.rjreid.adoptadog.ui.dogdetails

import ca.rjreid.adoptadog.data.DataManager

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import com.evernote.android.state.State
import com.evernote.android.state.StateSaver

class DogDetailsPresenter constructor(private var dogDetailsView: DogDetailsView, private var dataManager: DataManager) {
    //region Variables
    private val compositeDisposable = CompositeDisposable()
    //endregion

    //region State Variables
    @State var testVariable: String = ""
    //endregion

    //region Commands
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

    //region Click Helpers

    //endregion

    //region Helpers

    //endregion
}