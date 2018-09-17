package dkgroup.kz.onlinedoctor.presentation.launch

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface LaunchView : MvpView {

    fun initMainPatientScreen()
    fun initMainDocScreen()
    fun dispatchIncomingCall(userName: String?, userId: String?)
}