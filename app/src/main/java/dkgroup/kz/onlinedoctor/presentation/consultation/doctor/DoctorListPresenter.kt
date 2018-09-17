package dkgroup.kz.onlinedoctor.presentation.consultation.doctor

import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.entity.model.MedCard
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
public class DoctorListPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<DoctorListView>() {
    var medCard: MedCard? = null


    override fun attachView(view: DoctorListView?) {
        super.attachView(view)
        Log.e("attach", "attach")
        if (firebaseUser != null) {
            firestore.collection("users").whereEqualTo("userType", "doctor").get()
                    .addOnSuccessListener {
                        var users = it.toObjects(User::class.java)
                        viewState.showUsers(users)
                    }
        }
    }

    fun toDoctorDetails(email: String) {
        var bundle = Bundle()
        bundle.putString("email",email)
        router.navigateTo(Screens.DOCTOR_DETAILS_SCREEN, bundle)
    }

}