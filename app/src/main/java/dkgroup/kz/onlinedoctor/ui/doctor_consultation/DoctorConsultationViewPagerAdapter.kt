package dkgroup.kz.onlinedoctor.ui.doctor_consultation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.widget.DrawerLayout

import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.DoctorListFragment
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.PatientsFragment


class DoctorConsultationViewPagerAdapter internal constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val titles = arrayOf("My patients", "Helpdesk")

    internal var drawerLayout: DrawerLayout? = null

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return PatientsFragment()
            1 ->
                //                return DutyFragment.newInstance(clientId);
                return DoctorListFragment()
        //                return OrderServiceFragment.newInstance(clientId);
            else -> return null
        }


    }
    override fun getCount(): Int {
        return titles.size
    }


    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

}