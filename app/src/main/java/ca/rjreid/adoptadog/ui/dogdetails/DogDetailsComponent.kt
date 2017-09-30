package ca.rjreid.adoptadog.ui.dogdetails

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(DogDetailsModule::class))
interface DogDetailsComponent : AndroidInjector<DogDetailsActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DogDetailsActivity>()
}
