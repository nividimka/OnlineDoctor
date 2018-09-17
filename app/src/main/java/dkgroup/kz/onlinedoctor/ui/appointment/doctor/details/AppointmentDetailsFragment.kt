package dkgroup.kz.onlinedoctor.ui.appointment.doctor.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.details.AppointmentDetailsPresenter
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.details.AppointmentDetailsView
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.DoctorDetailsPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.DoctorDetailsView
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.book_appointment.BookAppointmentPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.book_appointment.BookAppointmentView
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment
import kotlinx.android.synthetic.main.appointment_details_fragment.*
import kotlinx.android.synthetic.main.time_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AppointmentDetailsFragment : MvpAppCompatFragment(), AppointmentDetailsView {
    override fun showAppointment(appointment: Appointment) {
        nameTV.text = appointment.user!!.firstName + " " + appointment.user!!.lastName

        var df = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        dateTV.text = df.format(appointment.date)
        timeTV.text = String.format("%02d:%02d", (appointment.time!! / 60000) / 60, (appointment.time!! / 60000) % 60)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: AppointmentDetailsPresenter

    @ProvidePresenter
    fun providePresenter(): AppointmentDetailsPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        presenter.appointmentId = arguments.getString("appointmentId")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.appointment_details_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onBackClick() }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): AppointmentDetailsFragment {
            val fragment = AppointmentDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

}
