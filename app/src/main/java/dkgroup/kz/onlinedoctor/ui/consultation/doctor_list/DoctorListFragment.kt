package dkgroup.kz.onlinedoctor.ui.consultation.doctor_list

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.App
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.User
import dkgroup.kz.onlinedoctor.entity.model.Doctor
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.DoctorListPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.doctor.DoctorListView
import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.DoctorListAdapter
import kotlinx.android.synthetic.main.doctor_list_fragment.*
import javax.inject.Inject


class DoctorListFragment : MvpAppCompatFragment(), DoctorListView {
    override fun showUsers(users: List<User>) {
        Log.e("users size = ", users.size.toString())
        var doctors: MutableList<Doctor> = ArrayList()
        users.forEach {
            doctors.add(Doctor(it.firstName, it.lastName, it.email))
        }
        adapter.swap(doctors)
    }

    ///@brief адаптер для табов, определяет в каком месте будет открываться определенный фрагмент, а так же их названия
    internal lateinit var adapter: DoctorListAdapter


    @Inject
    @InjectPresenter
    lateinit var presenter: DoctorListPresenter

    @ProvidePresenter
    fun providePresenter(): DoctorListPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.doctor_list_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DoctorListAdapter()
        adapter.clickListener = object : DoctorListAdapter.ClickListener {
            override fun onClick(email: String) {
                presenter.toDoctorDetails(email)
            }

        }
        doctorRV.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): DoctorListFragment {
            val fragment = DoctorListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }


}
