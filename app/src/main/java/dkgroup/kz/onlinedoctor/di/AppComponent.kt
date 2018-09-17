package dkgroup.kz.onlinedoctor.di


import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dkgroup.kz.onlinedoctor.App

@Singleton
@Component(modules = arrayOf(NavigationModule::class, LocalNavigationModule::class, FirebaseModule::class, AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    public fun inject(app: App)
}