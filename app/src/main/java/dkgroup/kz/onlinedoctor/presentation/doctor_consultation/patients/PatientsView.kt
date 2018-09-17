package dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.Patient

interface PatientsView : MvpView {
    fun showPatients(patients: List<Patient>)
    fun showEmpty()
}