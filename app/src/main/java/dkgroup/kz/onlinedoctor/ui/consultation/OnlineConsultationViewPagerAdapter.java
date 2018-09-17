package dkgroup.kz.onlinedoctor.ui.consultation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;

import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.DoctorListFragment;

public class OnlineConsultationViewPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{
            /*"Disease",*/ "Doctors", "Helpdesk"
    };

    OnlineConsultationViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
//                return new DiseaseFragment();
            case 0:
                return new DoctorListFragment();
            case 1:
                return new HelpdeskFragment();
//            case 2:
//                return DutyFragment.newInstance(clientId);
//                return OrderServiceFragment.newInstance(clientId);
            default:
                return null;
        }


    }

    DrawerLayout drawerLayout;

    @Override
    public int getCount() {
        return titles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}