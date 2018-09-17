package dkgroup.kz.onlinedoctor.di

import android.content.Context

import dagger.Module
import dagger.Provides
import dkgroup.kz.onlinedoctor.App
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.presentation.base.GlobalMenuController
import org.intellij.lang.annotations.Flow
import ru.terrakok.cicerone.Cicerone

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    fun getMenuController(): GlobalMenuController {
        return GlobalMenuController()
    }

}