package dkgroup.kz.onlinedoctor.di

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import dkgroup.kz.onlinedoctor.FlowRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router


@Module
class NavigationModule {
    val cicerone: Cicerone<FlowRouter>

    init {
        cicerone = Cicerone.create(FlowRouter())
    }

    @Provides
    @Singleton
    fun provideRouter(): FlowRouter {
        return cicerone.router
    }

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}
