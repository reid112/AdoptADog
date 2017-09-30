package ca.rjreid.adoptadog.di.component

import android.app.Application
import ca.rjreid.adoptadog.App
import ca.rjreid.adoptadog.di.module.ActivityBuilderModule
import ca.rjreid.adoptadog.di.module.AppModule
import ca.rjreid.adoptadog.di.module.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        DataModule::class
))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}