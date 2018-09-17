package dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.details

import android.os.Bundle
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
public class PatientDetailsPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<PatientDetailsView>() {
    var medCard: MedCard? = null
    var email: String? = null

    override fun attachView(view: PatientDetailsView?) {
        super.attachView(view)
        if (firebaseUser != null && email != null) {
            firestore.collection("users").whereEqualTo("email", email).get()
                    .addOnSuccessListener {
                        var users = it.toObjects(User::class.java)
                        if (users.size == 1) {
                            viewState.showUser(users[0])
                        }
                    }
        }
    }

    fun onBackClick() {
        router.exit()
    }

    fun makeCall() {
        if (firebaseUser != null && email != null && firebaseUser!!.email != null) {
            viewState.dispatchCall(email!!, firebaseUser!!.email!!)
        }
    }


    fun toDoctorNotes() {
        var bundle = Bundle()
        bundle.putString("email", email)
        router.navigateTo(Screens.DOCTOR_NOTES_SCREEN, bundle)
    }
}