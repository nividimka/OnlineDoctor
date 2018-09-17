package dkgroup.kz.onlinedoctor.presentation.appointment.doctor.details

import android.os.Bundle
import com.arellomobile.mvp.InjectViewState
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.entity.model.MedCard
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.book_appointment.BookAppointmentView
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment
import java.util.*
import javax.inject.Inject

@InjectViewState
public class AppointmentDetailsPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<AppointmentDetailsView>() {
    var medCard: MedCard? = null
    var appointmentId: String? = null

    override fun attachView(view: AppointmentDetailsView?) {
        super.attachView(view)
        if (firebaseUser != null && appointmentId != null) {
            firestore.collection("appointment").document(appointmentId!!).get()
                    .addOnSuccessListener { appointmentDoc ->

                        firestore.collection("users").whereEqualTo("email", appointmentDoc.data["patientEmail"]).get()
                                .addOnSuccessListener {
                                    var users = it.toObjects(User::class.java)
                                    if (users.size == 1) {
                                        var appointment = Appointment(appointmentDoc.id)
                                        appointment.user = users[0]
                                        appointment.status = appointmentDoc.data["status"] as String
                                        appointment.date = Date(appointmentDoc.data["day"] as Long)
                                        appointment.time = appointmentDoc.data["time"] as Long
                                        viewState.showAppointment(appointment)
                                    }
                                }
                    }
        }
    }

    fun onBackClick() {
        router.exit()
    }
}