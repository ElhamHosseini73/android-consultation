package ir.rayapars.consultation.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.fragments.CvFragment;
import ir.rayapars.consultation.fragments.MapFragment;
import ir.rayapars.consultation.fragments.ServiceFragment;

public class ClinicAdapter extends FragmentPagerAdapter {

    Context context;

    public ClinicAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new CvFragment();
        } else if (position == 1) {
            return new ServiceFragment();
        } else {
            return new MapFragment();
        }

    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return context.getString(R.string.cv);
            case 1:
                return context.getString(R.string.service);
            case 2:
                return context.getString(R.string.map);
            default:
                return null;
        }
    }
}
