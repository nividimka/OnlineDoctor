package dkgroup.kz.onlinedoctor.ui.notification


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.App
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.presentation.consultation.OnlineConsultationPresenter
import dkgroup.kz.onlinedoctor.presentation.consultation.OnlineConsultationView
import dkgroup.kz.onlinedoctor.ui.MainActivity
import kotlinx.android.synthetic.main.online_consultation_fragment.*
import javax.inject.Inject


class NotificationFragment : Fragment(), OnlineConsultationView {


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

    protected val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.notification_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onMenuClick() }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun onStop() {
        super.onStop()
    }

    companion object {

        fun newInstance(args: Bundle): NotificationFragment {
            val fragment = NotificationFragment()
            fragment.arguments = args
            return fragment
        }
    }


}