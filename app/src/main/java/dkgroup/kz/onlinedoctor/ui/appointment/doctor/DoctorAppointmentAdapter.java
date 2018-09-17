package dkgroup.kz.onlinedoctor.ui.appointment.doctor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;

import dkgroup.kz.onlinedoctor.ui.appointment.doctor.canceled.CanceledAppointmentFragment;
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.completed.CompletedAppointmentFragment;
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.upcoming.UpcomingAppointmentFragment;

public class DoctorAppointmentAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{
            "Completed", "Upcoming", "Canceled"
    };

    DoctorAppointmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new CompletedAppointmentFragment();
            case 1:
                return new UpcomingAppointmentFragment();
            case 2:
                return new CanceledAppointmentFragment();
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