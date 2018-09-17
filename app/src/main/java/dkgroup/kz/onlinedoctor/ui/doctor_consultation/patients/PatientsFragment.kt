package dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.upcoming.UpcomingAppointmentPresenter
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.upcoming.UpcomingAppointmentView
import dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.PatientsPresenter
import dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.PatientsView
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.AppointmentAdapter
import kotlinx.android.synthetic.main.upcoming_appointment_fragment.*
import javax.inject.Inject


class PatientsFragment : MvpAppCompatFragment(), PatientsView {

    override fun showEmpty() {
        empty.visibility = View.VISIBLE
        appointmentRV.visibility = View.GONE
    }

    override fun showPatients(patients: List<Patient>) {
        empty.visibility = View.GONE
        appointmentRV.visibility = View.VISIBLE
        adapter.swap(patients)
    }

    ///@brief адаптер для табов, определяет в каком месте будет открываться определенный фрагмент, а так же их названия
    internal lateinit var adapter: PatientsAdapter


    @Inject
    @InjectPresenter
    lateinit var presenter: PatientsPresenter


    @ProvidePresenter
    fun providePresenter(): PatientsPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.upcoming_appointment_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PatientsAdapter()
        adapter.clickListener = object : PatientsAdapter.ClickListener {
            override fun onClick(email: String) {
                presenter.toPatientsDetails(email)
            }
        }
        appointmentRV.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): PatientsFragment {
            val fragment = PatientsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }


}
