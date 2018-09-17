package dkgroup.kz.onlinedoctor.ui.medical_card

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.presentation.consultation.OnlineConsultationPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.OnlineConsultationView
import kotlinx.android.synthetic.main.online_consultation_fragment.*
import javax.inject.Inject


class MedicalCardFragment : Fragment(), OnlineConsultationView {
    ///@brief адаптер для табов, определяет в каком месте будет открываться определенный фрагмент, а так же их названия
    internal lateinit var adapter: MedicalCardViewPagerAdapter


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
        val view = inflater!!.inflate(R.layout.medical_fragment, container, false)
        adapter = MedicalCardViewPagerAdapter(childFragmentManager)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onMenuClick() }
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): MedicalCardFragment {
            val fragment = MedicalCardFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

}
