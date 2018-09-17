package dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.book_appointment

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User

interface BookAppointmentView : MvpView {
    fun showUser(user: User)
    fun showTimes(availableTimes: MutableList<Long>)
}
