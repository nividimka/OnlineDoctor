package dkgroup.kz.onlinedoctor.presentation.appointment.doctor.completed

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment

interface CompletedAppointmentView : MvpView{
    fun showAppointments(appointments: List<Appointment>)
    fun showEmpty()
}