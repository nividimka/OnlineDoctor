package dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.details.doctor_note

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.entity.model.MedCard
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.doctor_note.Note
import java.util.*
import javax.inject.Inject

@InjectViewState
public class DoctorPatientNotesPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firestore: FirebaseFirestore,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<DoctorPatientNotesView>() {
    var medCard: MedCard? = null
    var email: String? = null

    override fun attachView(view: DoctorPatientNotesView?) {
        super.attachView(view)
        reloadNotes()
    }

    fun reloadNotes() {
        Log.e("email", "mail" + email)
        if (firebaseUser != null && email != null) {
            firestore.collection("users").whereEqualTo("email", email).get()
                    .addOnSuccessListener {
                        Log.e("email", "mail" + it.size())
                        if (it.size() == 1) {
                            firestore.collection("users").document(it.documents.get(0).id).collection("notes").orderBy("date", Query.Direction.DESCENDING).get()
                                    .addOnSuccessListener {
                                        Log.e("email", "mail" + it.size())
                                        var notes: MutableList<Note> = ArrayList()
                                        it.forEach {
                                            var note: Note = Note()
                                            note.text = it.data["text"] as String
                                            note.date = Date(it.data["date"] as Long)
                                            notes.add(note)
                                        }
                                        viewState.showNotes(notes)
                                    }
                        }
                    }
        }
    }

    fun onBackClick() {
        router.exit()
    }

    fun addNote(note: String) {
        firestore.collection("users").whereEqualTo("email", email).get()
                .addOnSuccessListener {
                    if (it.size() == 1) {
                        var noteMap: MutableMap<String, Any?> = HashMap()
                        noteMap.put("text", note)
                        noteMap.put("email", firebaseUser!!.email!!)
                        noteMap.put("date", Calendar.getInstance().timeInMillis)
                        firestore.collection("users").document(it.documents.get(0).id).collection("notes").add(noteMap)
                                .addOnSuccessListener {
                                    viewState.clearEt()
                                    reloadNotes()
                                }
                    }
                }
    }

}