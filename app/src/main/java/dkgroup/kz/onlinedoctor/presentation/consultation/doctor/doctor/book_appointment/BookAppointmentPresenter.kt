package dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.book_appointment

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.Screens.CONSULTATION_SCREEN
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.entity.model.MedCard
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import java.util.*
import javax.inject.Inject

@InjectViewState
public class BookAppointmentPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<BookAppointmentView>() {
    var email: String? = null
    var docId: String? = null
    var time: Long? = null
    var day: Long? = null;

    override fun attachView(view: BookAppointmentView?) {
        super.attachView(view)
        if (firebaseUser != null) {
            firestore.collection("users").whereEqualTo("email", email).get()
                    .addOnSuccessListener {
                        if (it.documents.size == 1) {
                            docId = it.documents.get(0).id
                            var users = it.toObjects(User::class.java)
                            viewState.showUser(users[0])
                        }
                    }
        }
    }

    fun onBackClick() {
        router.exit()
    }

    fun bookAppointment() {
        if (firebaseUser != null) {
            val appointment: HashMap<String, Any> = HashMap()
            Log.e("patientEmail", firebaseUser.email!!)
            appointment["patientEmail"] = firebaseUser.email!!
            appointment["doctorEmail"] = email!!
            appointment["day"] = day!!
            appointment["time"] = time!!
            appointment["status"] = "new"
            firestore.collection("appointment")
                    .add(appointment)
                    .addOnCompleteListener {
                        router.backTo(Screens.CONSULTATION_SCREEN)
                    }
                    .addOnFailureListener(OnFailureListener {
                    })
        }
    }

    fun getAvailableTimes(day: Long?) {
        this.day = day
        this.time = null
        if (docId != null)
            firestore.collection("users").document(docId!!).collection("schedule")
                    .whereEqualTo("date", day)
                    .get()
                    .addOnSuccessListener {

                        var times: MutableList<Long> = ArrayList()
                        for (i in 12L..18L) {
                            times.add(i * 60 * 60 * 1000)
                            times.add((i * 60 + 30) * 60 * 1000)
                        }
                        var availableTimes: MutableList<Long> = ArrayList()
                        var disabledTimes: MutableList<Long> = ArrayList()
                        it.documents.forEach { doc ->
                            disabledTimes.add(doc.getLong("time"))
                        }
                        times.forEach { time ->
                            if (!disabledTimes.contains(time))
                                availableTimes.add(time)
                        }
                        viewState.showTimes(availableTimes)
                    }
    }
}