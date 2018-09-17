package dkgroup.kz.onlinedoctor.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.extensions.loadRoundedImage
import dkgroup.kz.onlinedoctor.presentation.drawer.NavigationDrawerPresenter
import dkgroup.kz.onlinedoctor.presentation.drawer.NavigationDrawerView
import dkgroup.kz.onlinedoctor.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_nav_drawer.*
import javax.inject.Inject

class NavigationDrawerFragment : BaseFragment(), NavigationDrawerView {

    override val layoutRes = R.layout.fragment_nav_drawer
    private var mainActivity: MainActivity? = null

    private var userId: Long? = null

    private val itemClickListener = { view: View ->
        presenter.onMenuItemClick(view.tag as NavigationDrawerView.MenuItem)
    }
    @Inject
    @InjectPresenter
    lateinit var presenter: NavigationDrawerPresenter

    @ProvidePresenter
    fun providePresenter(): NavigationDrawerPresenter {
        return presenter
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        avatarImageView.setOnClickListener { userId?.let { /* presenter.onUserClick(it)*/ } }
        logoutTV.setOnClickListener {
            presenter.onLogoutClick()
        }
    }

    override fun showDrawer(doctor: Boolean) {
        navDrawerMenuContainer.removeAllViews()
        if (doctor) {
            navDrawerMenuContainer.addView(addDrawerItem("My patients", NavigationDrawerView.MenuItem.PATIENTS))
            navDrawerMenuContainer.addView(addDrawerItem("Appointment", NavigationDrawerView.MenuItem.APPOINTMENT))
            navDrawerMenuContainer.addView(addDrawerItem("Notification", NavigationDrawerView.MenuItem.NOTIFICATION))
            navDrawerMenuContainer.addView(addDrawerItem("Support", NavigationDrawerView.MenuItem.SUPPORT))
        } else {
            navDrawerMenuContainer.addView(addDrawerItem("Online consultation", NavigationDrawerView.MenuItem.ONLINE_CONSULTATION))
            navDrawerMenuContainer.addView(addDrawerItem("Doctor appointment", NavigationDrawerView.MenuItem.DOCTOR_APPOINTMENT))
            navDrawerMenuContainer.addView(addDrawerItem("Medical card", NavigationDrawerView.MenuItem.MEDICAL_CARD))
            navDrawerMenuContainer.addView(addDrawerItem("My doctors", NavigationDrawerView.MenuItem.DOCTORS))
            navDrawerMenuContainer.addView(addDrawerItem("Notification", NavigationDrawerView.MenuItem.NOTIFICATION))
            navDrawerMenuContainer.addView(addDrawerItem("Support", NavigationDrawerView.MenuItem.SUPPORT))
        }
    }

    fun addDrawerItem(text: String, menuItem: NavigationDrawerView.MenuItem): View {
        var v: TextView
        v = LayoutInflater.from(context).inflate(R.layout.drawer_item, null) as TextView
        v.tag = menuItem
        v.text = text
        v.isSelected = presenter.currentSelectedItem == menuItem
        v.setOnClickListener(itemClickListener)
        return v
    }

    override fun showUserInfo(userName: String, userMail: String) {
        name.text = userName
        email.text = userMail
//        if (user.user == null) {
//            userId = null
//            nickTV.text = ""
//            serverNameTV.text = ""
//            avatarImageView.visibility = View.GONE
//        } else {
//            with(user.user) {
//                userId = this.id
//                nickTV.text = this.name
//                serverNameTV.text = user.serverName
        avatarImageView.loadRoundedImage("qweqqwe", context)
//            }
//        }
    }

    override fun selectMenuItem(item: NavigationDrawerView.MenuItem) {
        Log.e("log", "menu size = " + navDrawerMenuContainer.childCount)
        (0 until navDrawerMenuContainer.childCount)
                .map { navDrawerMenuContainer.getChildAt(it) }
                .forEach { menuItem ->
                    menuItem.tag?.let {
                        menuItem.isSelected = it == item
                    }
                }
    }

    fun onScreenChanged(item: NavigationDrawerView.MenuItem) {
        presenter.onScreenChanged(item)
    }

//    override fun dialogConfirm(tag: String) {
//        when (tag) {
//            CONFIRM_LOGOUT_TAG -> presenter.onLogoutClick()
//        }
//    }

    private companion object {
        private val CONFIRM_LOGOUT_TAG = "confirm_logout_tag"
    }
}