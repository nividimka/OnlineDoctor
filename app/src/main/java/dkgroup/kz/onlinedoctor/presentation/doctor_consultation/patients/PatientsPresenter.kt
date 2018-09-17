package dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients

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
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.Patient
import java.util.*
import javax.inject.Inject

@InjectViewState
class PatientsPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<PatientsView>() {
    var medCard: MedCard? = null
    var patients: MutableList<Patient> = ArrayList()

    override fun attachView(view: PatientsView?) {
        super.attachView(view)
        Log.e("attach", "attach")
        if (firebaseUser != null) {
            firestore.collection("users").document(firebaseUser.uid).collection("patients")
                    .get()
                    .addOnSuccessListener {
                        patients = ArrayList()
                        it.forEach {

                            var patient = Patient(it.data["firstName"] as String, it.data["lastName"] as String, it.id)
                            patients.add(patient)
                        }
                        viewState.showPatients(patients)

                        if (it.size() == 0) {
                            viewState.showEmpty()
                        }
                    }
        }
    }

    fun toDoctorDetails(email: String) {
    }

    fun toPatientsDetails(email: String) {
        var bundle = Bundle()
        bundle.putString("email", email)
        router.navigateTo(Screens.PATIENT_DETAILS_SCREEN, bundle)
    }

}