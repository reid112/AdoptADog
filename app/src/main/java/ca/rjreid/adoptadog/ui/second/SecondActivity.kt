package ca.rjreid.adoptadog.ui.second

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.ui.base.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_second.*
import javax.inject.Inject

class SecondActivity : BaseActivity(), SecondView {
    companion object {
        @JvmStatic
        fun createIntent(context: Context) = Intent(context, SecondActivity::class.java)
    }

    //region Variables
    @Inject lateinit var presenter: SecondPresenter
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        presenter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.restoreInstanceState(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
    //endregion

    //region BaseActivity Implementation
    override fun getLayout() = R.layout.activity_second

    override fun getToolbar() : Toolbar? = toolbar

    override fun shouldShowBackButton() = true
    //endregion
}