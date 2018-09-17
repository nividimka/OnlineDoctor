package dkgroup.kz.onlinedoctor.presentation.medical_card.notes

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.entity.model.MedCard
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
public class DoctorNotesPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<DoctorNotesView>() {


    override fun attachView(view: DoctorNotesView?) {
        super.attachView(view)
        if (firebaseUser != null) {
            firestore.collection("med_card").document(firebaseUser.email!!).collection("doctor_notes").get()
                    .addOnSuccessListener {
                        viewState.showNotes(it)
                    }
        }
    }

    fun onBackPressed() = router.finishChain()
}