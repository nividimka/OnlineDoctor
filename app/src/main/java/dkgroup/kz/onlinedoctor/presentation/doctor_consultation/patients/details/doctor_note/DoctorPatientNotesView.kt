package dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.details.doctor_note

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.doctor_note.Note

interface DoctorPatientNotesView : MvpView {
    fun showNotes(notes: List<Note>)
    fun clearEt()
}

