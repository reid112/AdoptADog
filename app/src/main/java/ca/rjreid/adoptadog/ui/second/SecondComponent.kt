package ca.rjreid.adoptadog.ui.second

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(SecondModule::class))
interface SecondComponent : AndroidInjector<SecondActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SecondActivity>()
}
