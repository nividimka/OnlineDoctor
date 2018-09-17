package dkgroup.kz.onlinedoctor.presentation.appointment.doctor.upcoming

import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.entity.model.MedCard
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment
import java.util.*
import javax.inject.Inject

@InjectViewState
class UpcomingAppointmentPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<UpcomingAppointmentView>() {
    var medCard: MedCard? = null
    var appointments: MutableList<Appointment> = ArrayList()

    override fun attachView(view: UpcomingAppointmentView?) {
        super.attachView(view)
        Log.e("attach", "attach")
        if (firebaseUser != null) {
            firestore.collection("appointment")
                    .whereEqualTo("doctorEmail", firebaseUser.email)
                    .whereEqualTo("status", "new")
                    .get()
                    .addOnSuccessListener {
                        Log.e("upcoming size = ", it.size().toString())
                        appointments = ArrayList()
                        it.forEach { appointmentDoc ->
                            firestore.collection("users").whereEqualTo("email", appointmentDoc.data["patientEmail"]).get()
                                    .addOnSuccessListener { users ->
                                        val localUsers = users.toObjects(User::class.java)
                                        localUsers.forEach {
                                            var appointment = Appointment(appointmentDoc.id)
                                            appointment.user = it
                                            appointment.status = appointmentDoc.data["status"] as String
                                            appointment.date = Date(appointmentDoc.data["day"] as Long)
                                            appointment.time = appointmentDoc.data["time"] as Long
                                            appointments.add(appointment)
                                        }
                                        viewState.showAppointments(appointments)
                                    }
                        }

                        if(it.size()==0){
                            viewState.showEmpty()
                        }
                    }
        }
    }

    fun toDoctorDetails(email: String) {
    }

    fun toAppointmentDetails(appointmentId: String) {
        var bundle = Bundle()
        bundle.putString("appointmentId", appointmentId)
        router.navigateTo(Screens.APPOINTMENT_DETAILS_SCREEN, bundle)
    }

}