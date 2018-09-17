package dkgroup.kz.onlinedoctor.presentation.medical_card.notes

import com.arellomobile.mvp.MvpView
import com.google.firebase.firestore.QuerySnapshot
import dkgroup.kz.onlinedoctor.entity.model.MedCard

interface DoctorNotesView :MvpView {
    fun showNotes(it: QuerySnapshot)
}
