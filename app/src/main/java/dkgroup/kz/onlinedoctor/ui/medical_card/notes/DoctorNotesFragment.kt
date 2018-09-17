package dkgroup.kz.onlinedoctor.ui.medical_card.notes


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.firebase.firestore.QuerySnapshot
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.entity.model.MedCard
import dkgroup.kz.onlinedoctor.presentation.consultation.OnlineConsultationPresenter
import dkgroup.kz.onlinedoctor.presentation.medical_card.notes.DoctorNotesPresenter
import dkgroup.kz.onlinedoctor.presentation.medical_card.notes.DoctorNotesView
import dkgroup.kz.onlinedoctor.presentation.medical_card.patient_info.PatientInfoPresenter
import dkgroup.kz.onlinedoctor.presentation.medical_card.patient_info.PatientInfoView
import dkgroup.kz.onlinedoctor.ui.base.BaseFragment
import dkgroup.kz.onlinedoctor.ui.medical_card.MedicalCardViewPagerAdapter
import kotlinx.android.synthetic.main.doctor_notes_fragment.*
import kotlinx.android.synthetic.main.patient_info_fragment.*
import javax.inject.Inject


class DoctorNotesFragment : BaseFragment(), DoctorNotesView {
    override fun showNotes(it: QuerySnapshot) {
        if (it.size() == 0) {
            empty.visibility = View.VISIBLE
        } else {
            empty.visibility = View.GONE
        }
    }

    override val layoutRes: Int = R.layout.doctor_notes_fragment


    @Inject
    @InjectPresenter
    lateinit var presenter: DoctorNotesPresenter

    @ProvidePresenter
    fun providePresenter(): DoctorNotesPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): DoctorNotesFragment {
            val fragment = DoctorNotesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

}
