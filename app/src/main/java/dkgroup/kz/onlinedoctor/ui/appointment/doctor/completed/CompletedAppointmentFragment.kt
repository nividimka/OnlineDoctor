package dkgroup.kz.onlinedoctor.ui.appointment.doctor.completed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.completed.CompletedAppointmentPresenter
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.completed.CompletedAppointmentView
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.AppointmentAdapter
import kotlinx.android.synthetic.main.upcoming_appointment_fragment.*
import javax.inject.Inject


class CompletedAppointmentFragment : MvpAppCompatFragment(), CompletedAppointmentView {

    override fun showEmpty() {
        empty.visibility = View.VISIBLE
        appointmentRV.visibility = View.GONE
    }

    override fun showAppointments(appointments: List<Appointment>) {
        empty.visibility = View.GONE
        appointmentRV.visibility = View.VISIBLE
        adapter.swap(appointments)
    }

    ///@brief адаптер для табов, определяет в каком месте будет открываться определенный фрагмент, а так же их названия
    internal lateinit var adapter: AppointmentAdapter


    @Inject
    @InjectPresenter
    lateinit var presenter: CompletedAppointmentPresenter


    @ProvidePresenter
    fun providePresenter(): CompletedAppointmentPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.completed_appointment_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AppointmentAdapter()
        adapter.clickListener = object : AppointmentAdapter.ClickListener {
            override fun onClick(appointmentId: String) {
                presenter.toAppointmentDetails(appointmentId)
            }
        }
        appointmentRV.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): CompletedAppointmentFragment {
            val fragment = CompletedAppointmentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }


}
