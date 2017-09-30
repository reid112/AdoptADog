package ca.rjreid.adoptadog

import android.app.Activity
import android.app.Application
import ca.rjreid.adoptadog.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject
import io.smooch.core.Settings
import io.smooch.core.Smooch
import io.smooch.core.SmoochCallback


class App : Application(), HasActivityInjector {
    //region Variables
    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    //endregion

    //region Lifecycle
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        initializeDagger()

        Smooch.init(this, Settings("59cf2696933506004059adb8"), SmoochCallback {
            // Your code after init is complete
        })
    }
    //endregion

    //region HasActivityInjector Implementation
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
    //endregion

    //region Helpers
    private fun initializeDagger() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
    //endregion
}