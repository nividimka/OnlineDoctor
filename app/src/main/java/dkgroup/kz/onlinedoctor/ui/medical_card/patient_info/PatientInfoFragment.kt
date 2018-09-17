package dkgroup.kz.onlinedoctor.ui.medical_card.patient_info


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.entity.model.MedCard
import dkgroup.kz.onlinedoctor.presentation.consultation.OnlineConsultationPresenter
import dkgroup.kz.onlinedoctor.presentation.medical_card.patient_info.PatientInfoPresenter
import dkgroup.kz.onlinedoctor.presentation.medical_card.patient_info.PatientInfoView
import dkgroup.kz.onlinedoctor.ui.base.BaseFragment
import dkgroup.kz.onlinedoctor.ui.medical_card.MedicalCardViewPagerAdapter
import kotlinx.android.synthetic.main.patient_info_fragment.*
import javax.inject.Inject


class PatientInfoFragment : BaseFragment(), PatientInfoView {
    override val layoutRes: Int = R.layout.patient_info_fragment

    @Inject
    @InjectPresenter
    lateinit var presenter: PatientInfoPresenter

    @ProvidePresenter
    fun providePresenter(): PatientInfoPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != "")
                    presenter.changeWeight(s.toString().toInt())
                else
                    presenter.changeWeight(0)
            }
        })
        height.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != "")
                    presenter.changeHeight(s.toString().toInt())
                else
                    presenter.changeHeight(0)
            }
        })
        chronical_diseases.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.changeDeseases(s.toString())
            }
        })
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): PatientInfoFragment {
            val fragment = PatientInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun showMedcard(medCard: MedCard) {
        if (medCard.weight != 0)
            weight.setText(medCard.weight.toString())
        if (medCard.height != 0)
            height.setText(medCard.height.toString())
        chronical_diseases.setText(medCard.chronicalDeseases)
    }
}
