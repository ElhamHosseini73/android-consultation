package ir.rayapars.consultation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.ItemInfiniteCycleViewPager;

public class InfiniteCycleViewPagerAdapter extends PagerAdapter {

    Context context;
    ItemInfiniteCycleViewPager[] item;
    LayoutInflater layoutInflater;

    public InfiniteCycleViewPagerAdapter(Context context, ItemInfiniteCycleViewPager[] item) {

        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }

/*    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

    *//*    View view = LayoutInflater.Inflate(R.layout.item_infinite_cycle_view_pager, container, false);
        ImageView imageView = view.f
        imageView.SetImageResource(ListImages[position]);
        container.AddView(view);
        return view;
*//*
        *//*  layoutInflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(_layouts[position], container, false);
        container.addView(view);

        return view;*//*

    }*/


}
