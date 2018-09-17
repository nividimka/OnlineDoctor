package dkgroup.kz.onlinedoctor.presentation.appointment.patient.details

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment

interface PatientAppointmentDetailsView : MvpView {
    fun showAppointment(user: Appointment)

}