package ca.rjreid.adoptadog.ui.second

import ca.rjreid.adoptadog.data.DataManager
import dagger.Module
import dagger.Provides

@Module
class SecondModule {

    @Provides
    internal fun provideSecondView(secondActivity: SecondActivity): SecondView {
        return secondActivity
    }

    @Provides
    internal fun provideSecondPresenter(secondView: SecondView, dataManager: DataManager): SecondPresenter {
        return SecondPresenter(secondView, dataManager)
    }
}
