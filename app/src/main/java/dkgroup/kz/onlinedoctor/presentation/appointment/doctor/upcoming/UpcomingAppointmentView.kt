package dkgroup.kz.onlinedoctor.presentation.appointment.doctor.upcoming

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment

interface UpcomingAppointmentView : MvpView{
    fun showAppointments(appointments: List<Appointment>)
    fun showEmpty()
}