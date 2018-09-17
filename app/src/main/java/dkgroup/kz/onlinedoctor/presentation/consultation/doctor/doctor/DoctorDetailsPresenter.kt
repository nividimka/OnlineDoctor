package dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor

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
import java.util.HashMap
import javax.inject.Inject

@InjectViewState
public class DoctorDetailsPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<DoctorDetailsView>() {
    var medCard: MedCard? = null
    var email: String? = null

    override fun attachView(view: DoctorDetailsView?) {
        super.attachView(view)
        if (firebaseUser != null) {
            firestore.collection("users").whereEqualTo("email", email).get()
                    .addOnSuccessListener {
                        var users = it.toObjects(User::class.java)
                        if (users.size == 1)
                            viewState.showUser(users[0])
                    }
        }
    }

    fun onBackClick() {
        router.exit()
    }

    fun bookAppointment() {
        var bundle = Bundle()
        bundle.putString("email",email)
        router.navigateTo(Screens.BOOK_APPOINTMENT_SCREEN, bundle)
    }
}