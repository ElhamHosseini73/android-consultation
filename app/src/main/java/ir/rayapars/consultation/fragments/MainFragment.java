package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.adapters.InfiniteCycleViewPagerAdapter;
import ir.rayapars.consultation.classes.ItemInfiniteCycleViewPager;
import ir.rayapars.consultation.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentMainBinding binding = FragmentMainBinding.inflate(getLayoutInflater());

        v = binding.getRoot();
        v.setClickable(true);
        v.setFocusable(true);

        ItemInfiniteCycleViewPager[] item = new ItemInfiniteCycleViewPager[]{new ItemInfiniteCycleViewPager(R.drawable.ic_department, "دپارتمان‌ها"), new ItemInfiniteCycleViewPager(R.drawable.ic_note, "درخواست همکاری"), new ItemInfiniteCycleViewPager(R.drawable.ic_calendar, "نوبت‌دهی آنلاین"), new ItemInfiniteCycleViewPager(R.drawable.ic_team, "مشاوران")};
        InfiniteCycleViewPagerAdapter infiniteCycleViewPagerAdapter = new InfiniteCycleViewPagerAdapter(v.getContext(), item);
        binding.hicvp.setAdapter(infiniteCycleViewPagerAdapter);

        return v;
    }
}
