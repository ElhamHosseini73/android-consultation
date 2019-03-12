package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import ir.rayapars.consultation.R;

public class TabHostFragment extends Fragment {

    View v;
    public static FragmentTabHost mTabHost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_tab_host, null);
        v.setFocusable(true);
        v.setClickable(true);

        mTabHost = (FragmentTabHost) v.findViewById(android.R.id.tabhost);
        mTabHost.setup(getContext(), getActivity().getSupportFragmentManager(), R.id.realtabcontent);

        View homeView = inflater.inflate(R.layout.view_main_page, null);
        View consultantView = inflater.inflate(R.layout.view_consultant, null);
        View ratingView = inflater.inflate(R.layout.view_rating, null);
        View clinicView = inflater.inflate(R.layout.view_clinic, null);
        View myAccountView = inflater.inflate(R.layout.view_my_account, null);

        mTabHost.addTab(mTabHost.newTabSpec("tab5").setIndicator(myAccountView),
                MyAccountFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator(clinicView),
                ClinicFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(ratingView),
                RatingFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(consultantView),
                ConsultantFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(homeView),
                MainFragment.class, null);


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                if (tabId.equals("tab2")) {

                    FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();

                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }


                }

                if (tabId.equals("tab3")) {

                    FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();

                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }


                }

                if (tabId.equals("tab4")) {

                    FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();

                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }


                }

                if (tabId.equals("tab5")) {

                    FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();

                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                }

            }
        });

        mTabHost.setCurrentTabByTag("tab1");

        return v;
    }
}
