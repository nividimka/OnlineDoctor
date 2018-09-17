package dkgroup.kz.onlinedoctor.presentation.drawer

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NavigationDrawerView : MvpView {
    enum class MenuItem {
        ONLINE_CONSULTATION,
        NOTIFICATION,
        PATIENTS,
        SUPPORT,
        DOCTORS,
        MEDICAL_CARD,
        DOCTOR_APPOINTMENT,
        APPOINTMENT,
        ABOUT
    }

//    fun showUserInfo(user: MyUserInfo)
    fun selectMenuItem(item: MenuItem)

    fun showDrawer(doctor: Boolean)
    fun showUserInfo(userName:String,userMail:String)
}