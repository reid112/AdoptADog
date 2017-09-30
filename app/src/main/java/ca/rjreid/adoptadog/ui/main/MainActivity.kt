package ca.rjreid.adoptadog.ui.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.data.DataManager
import ca.rjreid.adoptadog.ui.base.BaseActivity
import ca.rjreid.adoptadog.ui.list.ListController
import ca.rjreid.adoptadog.ui.mydog.MyDogController
import ca.rjreid.adoptadog.ui.profile.ProfileController
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import android.content.Intent




class MainActivity : BaseActivity(), MainView {
    //region Variables
    @Inject lateinit var presenter: MainPresenter
    private var router: Router? = null
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        router = Conductor.attachRouter(this, mainContent, savedInstanceState)
        presenter.create()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        presenter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.restoreInstanceState(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        router?.getControllerWithTag("Profile")?.onActivityResult(requestCode, resultCode, data)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
//        return when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onBackPressed() {
        router?.let {
            if (!it.handleBack()) {
                super.onBackPressed()
            } else {
                bottomNavigation.currentItem = 0
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
    //endregion

    //region View Implementation
    override fun initialize(dm: DataManager) {
        initializeConductor(dm)
        initializeBottomNavigation()
    }

    override fun showHome() {
        router?.popToRoot()
    }

    override fun showMyDog() {
        router?.pushController(RouterTransaction
                .with(MyDogController())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler())
                .tag("MyDog"))
    }

    override fun showProfile(dm: DataManager) {
        val profileController = ProfileController()
        profileController.setDataManager(dm)
        router?.pushController(RouterTransaction
                .with(profileController)
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler())
                .tag("Profile"))
    }
    //endregion

    //region Helpers
    private fun initializeConductor(dm: DataManager) {
        router?.let {
            if (!it.hasRootController()) {
                val listController = ListController()
                listController.setDataManager(dm)
                it.setRoot(RouterTransaction.with(listController).tag("List"))
            }
        }
    }

    private fun initializeBottomNavigation() {
        val item1 = AHBottomNavigationItem(R.string.tab_title_home, R.drawable.icon_home, R.color.colorAccent)
        val item2 = AHBottomNavigationItem(R.string.tab_title_my_dog, R.drawable.ic_dog, R.color.colorAccent)
        val item3 = AHBottomNavigationItem(R.string.tab_title_profile, R.drawable.icon_profile, R.color.colorAccent)

        bottomNavigation.addItem(item1)
        bottomNavigation.addItem(item2)
        bottomNavigation.addItem(item3)

        bottomNavigation.defaultBackgroundColor = ContextCompat.getColor(this, R.color.white)
        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottomNavigation.inactiveColor = ContextCompat.getColor(this, R.color.black)
        bottomNavigation.accentColor = ContextCompat.getColor(this, R.color.colorAccent)

        bottomNavigation.setOnTabSelectedListener(presenter)
    }
    //endregion

    //region BaseActivity Implementation
    override fun getLayout() = R.layout.activity_main

    override fun getToolbar() : Toolbar? = toolbar

    override fun shouldShowBackButton() = false
    //endregion
}
