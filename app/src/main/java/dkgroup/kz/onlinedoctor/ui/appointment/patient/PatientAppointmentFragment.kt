package dkgroup.kz.onlinedoctor.ui.appointment.patient

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


class PatientAppointmentFragment : Fragment(), OnlineConsultationView {
    ///@brief адаптер для табов, определяет в каком месте будет открываться определенный фрагмент, а так же их названия
    internal lateinit var adapter: PatientAppointmentAdapter


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
        val view = inflater!!.inflate(R.layout.appointment_fragment, container, false)
        adapter = PatientAppointmentAdapter(childFragmentManager)

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

        fun newInstance(args: Bundle): PatientAppointmentFragment {
            val fragment = PatientAppointmentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

    //    ToolbarButton counterButton;
    //
    //
    //    @Override
    //    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    //        inflater.inflate(R.menu.service_menu, menu);
    //        super.onCreateOptionsMenu(menu, inflater);
    //        MenuItem menuItem = menu.findItem(R.id.cart);
    //        counterButton = new ToolbarButton(menuItem.getActionView().findViewById(R.id.content));
    //        counterButton.show();
    //        counterButton.setCounterValue(cartServiceCountPresenter.getQuantity());
    //        counterButton.setActivated(false);
    //        counterButton.setOnClickListener(v ->cartServiceCountPresenter.navigateToCart());
    //        counterButton.setBackgroundVisible(true);
    //        counterButton.setActivated(false);
    //    }
    //
    //    @Override
    //    public void showQuantity(int totalQuantity) {
    //        if (counterButton != null)
    //            counterButton.setCounterValue(totalQuantity);
    //    }

}
