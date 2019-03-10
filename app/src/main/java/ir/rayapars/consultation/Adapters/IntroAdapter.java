package ir.rayapars.consultation.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IntroAdapter extends PagerAdapter {

    int[] _layouts;
    Activity _activity;
    LayoutInflater layoutInflater;

    public IntroAdapter(Activity a, int[] layouts) {

        _activity = a;
        _layouts = layouts;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(_layouts[position], container, false);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return _layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}