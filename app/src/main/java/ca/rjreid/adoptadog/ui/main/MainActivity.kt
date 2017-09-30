package ca.rjreid.adoptadog.ui.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.ui.base.BaseActivity
import ca.rjreid.adoptadog.ui.dogdetails.DogDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {
    //region Variables
    @Inject lateinit var presenter: MainPresenter
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        fab.setOnClickListener { _ ->
           startActivity(DogDetailsActivity.createIntent(this))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        presenter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.restoreInstanceState(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> return true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
    //endregion

    //region BaseActivity Implementation
    override fun getLayout() = R.layout.activity_main

    override fun getToolbar() : Toolbar? = toolbar

    override fun shouldShowBackButton() = false
    //endregion
}
