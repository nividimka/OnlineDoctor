package dkgroup.kz.onlinedoctor.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.ui.IncomingCallActivity
import dkgroup.kz.onlinedoctor.ui.MainActivity
import dkgroup.kz.onlinedoctor.ui.auth.AuthActivity

abstract class BaseActivity : MvpAppCompatActivity() {

    abstract val layoutRes: Int
//
//    override fun attachBaseContext(newBase: Context?) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }

    protected fun getFlowIntent(flowKey: String, data: Any?): Intent? = when (flowKey) {
        Screens.AUTH_FLOW -> AuthActivity.getStartIntent(this)
        Screens.MAIN_FLOW -> MainActivity.getStartIntent(this)
        else -> null
    }
}