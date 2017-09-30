package ca.rjreid.adoptadog.ui.dogdetails

import ca.rjreid.adoptadog.data.DataManager
import dagger.Module
import dagger.Provides

@Module
class DogDetailsModule {

    @Provides
    internal fun provideListView(dogDetailsActivity: DogDetailsActivity): DogDetailsView {
        return dogDetailsActivity
    }

    @Provides
    internal fun provideListPresenter(dogDetailsView: DogDetailsView, dataManager: DataManager): DogDetailsPresenter {
        return DogDetailsPresenter(dogDetailsView, dataManager)
    }
}
