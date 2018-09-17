package dkgroup.kz.onlinedoctor.ui.medical_card;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;

import java.lang.reflect.Field;

import dkgroup.kz.onlinedoctor.ui.medical_card.notes.DoctorNotesFragment;
import dkgroup.kz.onlinedoctor.ui.medical_card.patient_info.PatientInfoFragment;

public class MedicalCardViewPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{
            "My card", "Doctor's notes"
    };

    MedicalCardViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new PatientInfoFragment();
            case 1:
                return new DoctorNotesFragment();
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