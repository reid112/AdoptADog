package ca.rjreid.adoptadog.di.module

import android.app.Activity
import ca.rjreid.adoptadog.ui.dogdetails.DogDetailsActivity
import ca.rjreid.adoptadog.ui.dogdetails.DogDetailsComponent
import ca.rjreid.adoptadog.ui.main.MainActivity
import ca.rjreid.adoptadog.ui.main.MainComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class ActivityBuilderModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(DogDetailsActivity::class)
    internal abstract fun bindListActivity(builder: DogDetailsComponent.Builder): AndroidInjector.Factory<out Activity>
}
