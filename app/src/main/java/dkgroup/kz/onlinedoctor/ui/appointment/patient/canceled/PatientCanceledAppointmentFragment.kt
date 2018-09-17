package dkgroup.kz.onlinedoctor.ui.appointment.patient.canceled

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.canceled.CanceledAppointmentPresenter
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.canceled.CanceledAppointmentView
import dkgroup.kz.onlinedoctor.presentation.appointment.patient.canceled.PatientCanceledAppointmentPresenter
import dkgroup.kz.onlinedoctor.presentation.appointment.patient.canceled.PatientCanceledAppointmentView
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.AppointmentAdapter
import kotlinx.android.synthetic.main.upcoming_appointment_fragment.*
import javax.inject.Inject


class PatientCanceledAppointmentFragment : MvpAppCompatFragment(), PatientCanceledAppointmentView {
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
    lateinit var presenter: PatientCanceledAppointmentPresenter


    @ProvidePresenter
    fun providePresenter(): PatientCanceledAppointmentPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.canceled_appointment_fragment, container, false)
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

        fun newInstance(args: Bundle): PatientCanceledAppointmentFragment {
            val fragment = PatientCanceledAppointmentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }


}
