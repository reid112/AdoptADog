package ca.rjreid.adoptadog.di.module

import android.app.Application
import android.content.Context
import ca.rjreid.adoptadog.di.qualifier.ApplicationContext
import ca.rjreid.adoptadog.ui.dogdetails.DogDetailsComponent
import ca.rjreid.adoptadog.ui.main.MainComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = arrayOf(MainComponent::class, DogDetailsComponent::class))
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(application: Application): Context {
        return application
    }
}