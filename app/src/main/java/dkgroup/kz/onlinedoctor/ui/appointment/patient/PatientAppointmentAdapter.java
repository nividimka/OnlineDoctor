package dkgroup.kz.onlinedoctor.ui.appointment.patient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;

import dkgroup.kz.onlinedoctor.ui.appointment.patient.canceled.PatientCanceledAppointmentFragment;
import dkgroup.kz.onlinedoctor.ui.appointment.patient.completed.PatientCompletedAppointmentFragment;
import dkgroup.kz.onlinedoctor.ui.appointment.patient.upcoming.PatientUpcomingAppointmentFragment;
import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.DoctorListFragment;

public class PatientAppointmentAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{
            "Completed", "Upcoming", "Canceled"
    };

    PatientAppointmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new PatientCompletedAppointmentFragment();
            case 1:
                return new PatientUpcomingAppointmentFragment();
            case 2:
                return new PatientCanceledAppointmentFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}