package dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.doctor_details

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
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.DoctorDetailsPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.DoctorDetailsView
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.book_appointment.BookAppointmentPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.book_appointment.BookAppointmentView
import kotlinx.android.synthetic.main.doctor_details_fragment.*
import javax.inject.Inject


class DoctorDetailsFragment : MvpAppCompatFragment(), DoctorDetailsView {
    override fun showUser(user: User) {
        nameTV.setText(user.firstName + " " + user.lastName)
    }


    @Inject
    @InjectPresenter
    lateinit var presenter: DoctorDetailsPresenter

    @ProvidePresenter
    fun providePresenter(): DoctorDetailsPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        presenter.email = arguments.getString("email")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.doctor_details_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onBackClick() }
        bookBtn.setOnClickListener{
            presenter.bookAppointment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): DoctorDetailsFragment {
            val fragment = DoctorDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

}
