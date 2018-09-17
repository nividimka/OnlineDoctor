package dkgroup.kz.onlinedoctor.presentation.medical_card.patient_info

import com.arellomobile.mvp.MvpView
import dkgroup.kz.onlinedoctor.entity.model.MedCard

interface PatientInfoView :MvpView {
    fun showMedcard(medCard: MedCard)
}
