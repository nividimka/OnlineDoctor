package dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User

interface DoctorDetailsView : MvpView {
    fun showUser(user: User)
}
