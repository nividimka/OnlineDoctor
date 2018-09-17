package dkgroup.kz.onlinedoctor.presentation.drawer

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dkgroup.kz.onlinedoctor.App
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import dkgroup.kz.onlinedoctor.presentation.base.GlobalMenuController
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class NavigationDrawerPresenter @Inject constructor(
        private val router: FlowRouter
) : BasePresenter<NavigationDrawerView>() {

    private var firebaseUser: FirebaseUser? = null

    private var db: FirebaseFirestore? = null

    var currentSelectedItem: NavigationDrawerView.MenuItem? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        firebaseUser = FirebaseAuth.getInstance().currentUser
        db = FirebaseFirestore.getInstance()
        if (firebaseUser != null)
            db!!.collection("users").document(firebaseUser!!.uid)
                    .get().addOnCompleteListener(OnCompleteListener {
                        if (it.isSuccessful()) {
                            val document: DocumentSnapshot = it.result
                            if (document.exists()) {
                                viewState.showDrawer(document.data.get("userType")!!.equals("doctor"))
                                viewState.showUserInfo(document.data.get("firstName") as String + " " + document.data.get("lastName") as String, firebaseUser!!.email!!)
                                Log.d("HELLO", "DocumentSnapshot data: " + document.data);
                            } else {
                                Log.d("HELLO", "No such document")
                            }
                        } else {
                            Log.d("HELLO", "get failed with ", it.exception)
                        }
                    })

//        myProfileInteractor.getMyProfile()
//                .subscribe(
//                        { viewState.showUserInfo(it) },
//                        { errorHandler.proceed(it) }
//                )
//                .connect()
    }

    fun onScreenChanged(item: NavigationDrawerView.MenuItem) {
        App.INSTANCE.menuController.close()
        currentSelectedItem = item
        viewState.selectMenuItem(item)
    }

    fun onMenuItemClick(item: NavigationDrawerView.MenuItem) {
        App.INSTANCE.menuController.close()
        if (item != currentSelectedItem) {
            when (item) {
                NavigationDrawerView.MenuItem.ONLINE_CONSULTATION -> router.newRootScreen(Screens.CONSULTATION_SCREEN)
                NavigationDrawerView.MenuItem.PATIENTS -> router.newRootScreen(Screens.DOCTOR_CONSULTATION_SCREEN)
                NavigationDrawerView.MenuItem.NOTIFICATION -> router.newRootScreen(Screens.NOTIFICATION_SCREEN)
                NavigationDrawerView.MenuItem.SUPPORT -> router.newRootScreen(Screens.SUPPORT_SCREEN)
                NavigationDrawerView.MenuItem.MEDICAL_CARD -> router.newRootScreen(Screens.MEDICAL_CARD_SCREEN)
                NavigationDrawerView.MenuItem.APPOINTMENT -> router.newRootScreen(Screens.DOCTOR_APPOINTMENT_SCREEN)
                NavigationDrawerView.MenuItem.DOCTOR_APPOINTMENT -> router.newRootScreen(Screens.PATIENT_APPOINTMENT_SCREEN)
            }
        }
    }

    fun onLogoutClick() {
        FirebaseAuth.getInstance().signOut()
        router.startFlow(Screens.AUTH_FLOW)
    }

//    fun onUserClick(id: Long) = router.startFlow(Screens.USER_FLOW, id)
}