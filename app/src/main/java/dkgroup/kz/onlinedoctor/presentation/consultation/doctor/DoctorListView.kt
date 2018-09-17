package dkgroup.kz.onlinedoctor.presentation.consultation.doctor

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User

interface DoctorListView : MvpView {
    fun showUsers(users: List<User>)
}
