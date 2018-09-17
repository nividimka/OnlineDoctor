package dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.pubnub.api.Callback
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.Constants
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.details.AppointmentDetailsPresenter
import dkgroup.kz.onlinedoctor.presentation.appointment.doctor.details.AppointmentDetailsView
import dkgroup.kz.onlinedoctor.presentation.appointment.patient.details.PatientAppointmentDetailsPresenter
import dkgroup.kz.onlinedoctor.presentation.appointment.patient.details.PatientAppointmentDetailsView
import dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.details.PatientDetailsPresenter
import dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.details.PatientDetailsView
import dkgroup.kz.onlinedoctor.ui.MainActivity
import dkgroup.kz.onlinedoctor.ui.VideoChatActivity
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.Appointment
import kotlinx.android.synthetic.main.patient_details_fragment.*
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class PatientDetailsFragment : MvpAppCompatFragment(), PatientDetailsView {
    override fun showUser(user: User) {
        nameTV.text = user!!.firstName + " " + user!!.lastName

        var df = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: PatientDetailsPresenter

    @ProvidePresenter
    fun providePresenter(): PatientDetailsPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        presenter.email = arguments.getString("email")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.patient_details_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onBackClick() }
        callBtn.setOnClickListener {
            presenter.makeCall()
        }
        doctorNotes.setOnClickListener {
            presenter.toDoctorNotes()
        }
    }

    override fun dispatchCall(userId: String, userName: String) {
        Log.e("email ", userId)
        Log.e("userName ", userName)
        val callNumStdBy = userId + Constants.STDBY_SUFFIX
        (activity as MainActivity).presenter.pubnub!!.hereNow(callNumStdBy, object : Callback() {
            override fun successCallback(channel: String?, message: Any) {
                Log.d("MA-dC", "HERE_NOW: " + " CH - " + callNumStdBy + " " + message.toString())
                try {
                    val occupancy = (message as JSONObject).getInt(Constants.JSON_OCCUPANCY)
                    if (occupancy == 0) {
                        activity.runOnUiThread {
                            Toast.makeText(context, "User is not online!", Toast.LENGTH_LONG).show()
                        }
                        return
                    }
                    val jsonCall = JSONObject()
                    jsonCall.put(Constants.JSON_CALL_USER, userName)
                    jsonCall.put(Constants.JSON_CALL_TIME, System.currentTimeMillis())
                    (activity as MainActivity).presenter.pubnub!!.publish(callNumStdBy, jsonCall, object : Callback() {
                        override fun successCallback(channel: String?, message: Any) {
                            Log.d("MA-dC", "SUCCESS: " + message.toString())
                            val intent = Intent(activity, VideoChatActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, userName)
                            intent.putExtra(Constants.CALL_USER, userId)  // Only accept from this number?
                            intent.putExtra("dialed", true)
                            startActivity(intent)
                        }
                    })
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): PatientDetailsFragment {
            val fragment = PatientDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

}
