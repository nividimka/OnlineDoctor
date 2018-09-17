package dkgroup.kz.onlinedoctor

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.v4.app.Fragment
import com.crashlytics.android.Crashlytics
import com.google.firebase.FirebaseApp
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import dkgroup.kz.onlinedoctor.di.DaggerAppComponent
import dkgroup.kz.onlinedoctor.presentation.base.GlobalMenuController
import io.fabric.sdk.android.Fabric
import javax.inject.Inject


class App : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    internal lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    internal lateinit var menuController: GlobalMenuController


    companion object {
        public lateinit var INSTANCE: App


    }




    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
        Fabric.with(this, Crashlytics())
        INSTANCE = this
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

    }


    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment>? {
        return fragmentInjector
    }


    protected override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
