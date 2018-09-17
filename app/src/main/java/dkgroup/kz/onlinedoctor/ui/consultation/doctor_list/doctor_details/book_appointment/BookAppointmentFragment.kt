package dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.doctor_details.book_appointment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.book_appointment.BookAppointmentPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.doctor.book_appointment.BookAppointmentView
import kotlinx.android.synthetic.main.book_appointment_fragment.*
import java.sql.Time
import java.util.*
import javax.inject.Inject

class BookAppointmentFragment : MvpAppCompatFragment(), BookAppointmentView {
    override fun showTimes(availableTimes: MutableList<Long>) {
        Log.e("times", availableTimes.size.toString())
        adapter.swap(availableTimes)
    }

    override fun showUser(user: User) {
        nameTV.setText(user.firstName + " " + user.lastName)
    }


    @Inject
    @InjectPresenter
    lateinit var presenter: BookAppointmentPresenter

    lateinit var adapter: TimeAdapter

    @ProvidePresenter
    fun providePresenter(): BookAppointmentPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        presenter.email = arguments.getString("email")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.book_appointment_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onBackClick() }
        bookBtn.setOnClickListener {
            if (presenter.time == null || presenter.day == null) {
                Toast.makeText(context, "Выберите начало посещения", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            presenter.bookAppointment()
        }
        calendar.setOnDayClickListener {
            presenter.getAvailableTimes(it.calendar.timeInMillis);
        }
        timesRV.layoutManager = GridLayoutManager(context, 3)
        adapter = TimeAdapter()
        adapter.clickListener = object : TimeAdapter.ClickListener {
            override fun onClick(time: Long, position: Int) {
                presenter.time = time
            }

        }
        timesRV.adapter = adapter
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): BookAppointmentFragment {
            val fragment = BookAppointmentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

}
