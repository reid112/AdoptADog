package ca.rjreid.adoptadog.ui.main

import ca.rjreid.adoptadog.data.DataManager
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    internal fun provideMainView(mainActivity: MainActivity): MainView {
        return mainActivity
    }

    @Provides
    internal fun provideMainPresenter(mainView: MainView, dataManager: DataManager): MainPresenter {
        return MainPresenter(mainView, dataManager)
    }
}