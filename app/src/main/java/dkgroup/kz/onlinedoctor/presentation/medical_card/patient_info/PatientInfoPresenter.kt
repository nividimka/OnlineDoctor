package dkgroup.kz.onlinedoctor.presentation.medical_card.patient_info

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
public class PatientInfoPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<PatientInfoView>() {
    var medCard: MedCard? = null


    override fun attachView(view: PatientInfoView?) {
        super.attachView(view)
        if (firebaseUser != null) {
            firestore.collection("med_card").document(firebaseUser.email!!).get()
                    .addOnSuccessListener {
                        medCard = MedCard.parseObject(it.data)
                        viewState.showMedcard(medCard!!)
                    }
        }
    }

    override fun detachView(view: PatientInfoView?) {
        if (medCard != null && firebaseUser != null) {
            firestore.collection("med_card")
                    .document(firebaseUser.email!!)
                    .set(medCard!!.toMap(), SetOptions.merge())

        }
        super.detachView(view)
    }


    fun onBackPressed() = router.finishChain()
    fun changeWeight(i: Int) {
        if (medCard != null)
            medCard!!.weight = i
    }

    fun changeHeight(i: Int) {
        if (medCard != null)
            medCard!!.height = i
    }

    fun changeDeseases(s: String) {
        if(medCard!=null)
            medCard!!.chronicalDeseases = s
    }
}