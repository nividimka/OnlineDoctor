package dkgroup.kz.onlinedoctor.presentation.appointment.doctor.details

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment

interface AppointmentDetailsView : MvpView {
    fun showAppointment(user: Appointment)

}