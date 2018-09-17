package dkgroup.kz.onlinedoctor.ui.consultation

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.App
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.presentation.consultation.OnlineConsultationPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.OnlineConsultationView
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.DoctorListPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.DoctorListView
import javax.inject.Inject


class HelpdeskFragment : Fragment(), OnlineConsultationView {
    ///@brief адаптер для табов, определяет в каком месте будет открываться определенный фрагмент, а так же их названия
    internal lateinit var adapter: OnlineConsultationViewPagerAdapter


    @Inject
    @InjectPresenter
    lateinit var presenter: OnlineConsultationPresenter

    @ProvidePresenter
    fun providePresenter(): OnlineConsultationPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.doctor_list_fragment, container, false)
        adapter = OnlineConsultationViewPagerAdapter(childFragmentManager)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): OnlineConsultationFragment {
            val fragment = OnlineConsultationFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

}
