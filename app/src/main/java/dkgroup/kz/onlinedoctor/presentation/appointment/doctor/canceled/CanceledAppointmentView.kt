package dkgroup.kz.onlinedoctor.presentation.appointment.doctor.canceled

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment

interface CanceledAppointmentView : MvpView{
    fun showAppointments(appointments: List<Appointment>)
    fun showEmpty()
}