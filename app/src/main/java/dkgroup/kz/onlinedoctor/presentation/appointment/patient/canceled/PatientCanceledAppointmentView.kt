package dkgroup.kz.onlinedoctor.presentation.appointment.patient.canceled

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment

interface PatientCanceledAppointmentView : MvpView{
    fun showAppointments(appointments: List<Appointment>)
    fun showEmpty()
}