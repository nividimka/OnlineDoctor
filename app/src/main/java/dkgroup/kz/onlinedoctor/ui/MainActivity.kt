package dkgroup.kz.onlinedoctor.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.ViewDragHelper
import android.util.Log
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dagger.android.AndroidInjection
import dkgroup.kz.onlinedoctor.*
import dkgroup.kz.onlinedoctor.presentation.drawer.NavigationDrawerView
import dkgroup.kz.onlinedoctor.presentation.launch.LaunchPresenter
import dkgroup.kz.onlinedoctor.presentation.launch.LaunchView
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.DoctorAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.details.AppointmentDetailsFragment
import dkgroup.kz.onlinedoctor.ui.appointment.patient.PatientAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.base.BaseActivity
import dkgroup.kz.onlinedoctor.ui.consultation.OnlineConsultationFragment
import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.doctor_details.DoctorDetailsFragment
import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.doctor_details.book_appointment.BookAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.medical_card.MedicalCardFragment
import dkgroup.kz.onlinedoctor.ui.notification.NotificationFragment
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.DoctorConsultationFragment
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.PatientDetailsFragment
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.doctor_note.DoctorPatientNotesFragment
import dkgroup.kz.onlinedoctor.ui.support.SupportFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject


class MainActivity : BaseActivity(), LaunchView {
    override fun dispatchIncomingCall(userName: String?, userId: String?) {
        Log.e("user", "user" + userName)
        val intent = Intent(this@MainActivity, IncomingCallActivity::class.java)
        intent.putExtra(Constants.USER_NAME, userName)
        intent.putExtra(Constants.CALL_USER, userId)
        startActivity(intent)
    }

    @Inject
    lateinit var navigationHolder: NavigatorHolder
    @Inject
    lateinit var router: FlowRouter

    override val layoutRes = R.layout.activity_main
    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null
    private var db: FirebaseFirestore? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null

    private var menuStateDisposable: Disposable? = null

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.mainContainer) as Fragment?

    private val drawerFragment
        get() = supportFragmentManager.findFragmentById(R.id.navDrawerContainer) as NavigationDrawerFragment?


    @Inject
    @InjectPresenter
    lateinit var presenter: LaunchPresenter

    @ProvidePresenter
    fun providePresenter(): LaunchPresenter {
        return presenter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initDrawer()
        db = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                router.startFlow(Screens.AUTH_FLOW)
            }
        }
//        initDrawer(true, savedInstanceState)
        val sharedPref = this@MainActivity.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser == null) {
            //Not signed in, launch the Sign In Activity
            router.startFlow(Screens.AUTH_FLOW)
            return
        } else {
            db!!.collection("users").document(firebaseUser!!.uid)
                    .get().addOnCompleteListener(OnCompleteListener {
                        if (it.isSuccessful()) {
                            val document: DocumentSnapshot = it.result
                            var userType = document.data.get("userType") as String
                            if (userType.equals("doctor")) {
                                initMainDocScreen()
                            } else {
                                initMainPatientScreen()
                            }
                        } else {
                            Log.d("HELLO", "get failed with ", it.exception)
                        }
                    })
        }
        //create the drawer and remember the `Drawer` result object
    }

    private fun initDrawer() {
        try {
            val dragger = drawerLayout!!.javaClass.getDeclaredField("mLeftDragger")
            dragger.isAccessible = true
            var draggerObj: ViewDragHelper = dragger
                    .get(drawerLayout) as ViewDragHelper
            val mEdgeSize = draggerObj.javaClass.getDeclaredField(
                    "mEdgeSize")
            mEdgeSize.isAccessible = true
            val edge = mEdgeSize.getInt(draggerObj)
            mEdgeSize.setInt(draggerObj, edge * 3)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }

    override fun initMainPatientScreen() {

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainer, OnlineConsultationFragment())
                .replace(R.id.navDrawerContainer, NavigationDrawerFragment())
                .commitNow()
        updateNavDrawer()
    }

    override fun initMainDocScreen() {

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainer, DoctorConsultationFragment())
                .replace(R.id.navDrawerContainer, NavigationDrawerFragment())
                .commitNow()
        updateNavDrawer()
    }

    private fun updateNavDrawer() {
        supportFragmentManager.executePendingTransactions()
        drawerFragment?.let { drawerFragment ->
            currentFragment?.let {
                when (it) {
                    is OnlineConsultationFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.ONLINE_CONSULTATION)
                    is DoctorConsultationFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.PATIENTS)
                    is SupportFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.SUPPORT)
                    is NotificationFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.NOTIFICATION)
                    is DoctorAppointmentFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.APPOINTMENT)
                    is PatientAppointmentFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.DOCTOR_APPOINTMENT)
                    is MedicalCardFragment -> drawerFragment.onScreenChanged(NavigationDrawerView.MenuItem.MEDICAL_CARD)
                }
                enableNavDrawer(isNavDrawerAvailableForFragment(it))
            }
        }
    }

    private fun isNavDrawerAvailableForFragment(currentFragment: Fragment) = when (currentFragment) {
        is OnlineConsultationFragment -> true
        is DoctorConsultationFragment -> true
        is SupportFragment -> true
        is NotificationFragment -> true
        is PatientAppointmentFragment -> true
        is DoctorAppointmentFragment -> true
        is MedicalCardFragment -> true
        else -> false
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        menuStateDisposable = App.INSTANCE.menuController.state.subscribe { openNavDrawer(it) }
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        menuStateDisposable?.dispose()
        navigationHolder.removeNavigator()
        super.onPause()
    }

    private fun openNavDrawer(open: Boolean) {
        Log.e("open", "open")

        drawerLayout.postDelayed({
            if (open) drawerLayout.openDrawer(GravityCompat.START)
            else drawerLayout.closeDrawer(GravityCompat.START)
        }, 150)
    }

    private fun enableNavDrawer(enable: Boolean) {
        drawerLayout.setDrawerLockMode(
                if (enable) DrawerLayout.LOCK_MODE_UNLOCKED
                else DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                GravityCompat.START
        )
    }

    private val navigator = object : FlowNavigator(this, R.id.mainContainer) {

        override fun applyCommands(commands: Array<out Command>?) {
            super.applyCommands(commands)
            updateNavDrawer()
        }

        override fun createFlowIntent(flowKey: String, data: Any?) = getFlowIntent(flowKey, data)


        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            Screens.CONSULTATION_SCREEN -> OnlineConsultationFragment()
            Screens.DOCTOR_CONSULTATION_SCREEN -> DoctorConsultationFragment()
            Screens.DOCTOR_DETAILS_SCREEN -> DoctorDetailsFragment.newInstance(data as Bundle)
            Screens.APPOINTMENT_DETAILS_SCREEN -> AppointmentDetailsFragment.newInstance(data as Bundle)
            Screens.BOOK_APPOINTMENT_SCREEN -> BookAppointmentFragment.newInstance(data as Bundle)
            Screens.SUPPORT_SCREEN -> SupportFragment()
            Screens.PATIENT_DETAILS_SCREEN -> PatientDetailsFragment.newInstance(data as Bundle)
            Screens.DOCTOR_APPOINTMENT_SCREEN -> DoctorAppointmentFragment()
            Screens.MEDICAL_CARD_SCREEN -> MedicalCardFragment()
            Screens.PATIENT_APPOINTMENT_SCREEN -> PatientAppointmentFragment()
            Screens.DOCTOR_NOTES_SCREEN -> DoctorPatientNotesFragment.newInstance(data as Bundle)
            Screens.NOTIFICATION_SCREEN -> NotificationFragment()
            else -> null
        }
    }

    public override fun onStart() {
        super.onStart()
        if (authListener != null)
            firebaseAuth!!.addAuthStateListener(authListener!!)
    }

    public override fun onStop() {
        super.onStop()
        if (authListener != null) {
            firebaseAuth!!.removeAuthStateListener(authListener!!)
        }
    }

    companion object {
        fun getStartIntent(context: Context) =
                Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
    }
}
