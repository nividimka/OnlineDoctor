package dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.details

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment

interface PatientDetailsView : MvpView {
    fun showUser(user: User)
    fun dispatchCall(userId: String, userName: String)

}