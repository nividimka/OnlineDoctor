package dkgroup.kz.onlinedoctor.ui.auth

import android.content.Context
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.presentation.auth.EntryView
import dkgroup.kz.onlinedoctor.presentation.auth.EntryPresenter
import dkgroup.kz.onlinedoctor.ui.base.BaseFragment
import kotlinx.android.synthetic.main.entry_fragment.*
import javax.inject.Inject

class EntryFragment : BaseFragment(), EntryView {

    override val layoutRes = R.layout.entry_fragment

    @InjectPresenter
    @Inject
    lateinit var presenter: EntryPresenter


    @Inject
    lateinit var router: FlowRouter;

    @ProvidePresenter
    fun providePresenter(): EntryPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        patient.setOnClickListener {
            val sharedPref = context.getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("userType", "patient")
                commit()
            }
            router.navigateTo(Screens.SIGN_IN_SCREEN,"patient")
        }
        doctor.setOnClickListener {
            val sharedPref = context.getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("userType", "doctor")
                commit()
            }
            router.navigateTo(Screens.SIGN_IN_SCREEN,"doctor")
        }
    }

}