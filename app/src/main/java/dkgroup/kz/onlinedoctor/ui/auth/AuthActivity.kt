package dkgroup.kz.onlinedoctor.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.ui.FlowNavigator
import dkgroup.kz.onlinedoctor.ui.base.BaseActivity
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class AuthActivity : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_auth

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        if (savedInstanceState == null) {
            navigator.setLaunchScreen(Screens.ENTRY_SCREEN, null)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    private val navigator = object : FlowNavigator(this, R.id.container) {

        override fun createFlowIntent(flowKey: String, data: Any?) = getFlowIntent(flowKey, data)

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            Screens.ENTRY_SCREEN -> EntryFragment()
            Screens.SIGN_IN_SCREEN -> SignInFragment.newInstance(data as String)
            Screens.SIGN_UP_SCREEN -> SignUpFragment.newInstance(data as String)
            else -> null
        }
    }

    companion object {
        fun getStartIntent(context: Context) =
                Intent(context, AuthActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
    }
}
