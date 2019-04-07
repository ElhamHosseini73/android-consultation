package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.adapters.ContentAdapter;
import ir.rayapars.consultation.databinding.FragmentContentBinding;

public class ContentFragment extends Fragment {

    View x;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        FragmentContentBinding binding = FragmentContentBinding.inflate(getLayoutInflater());
        x = binding.getRoot();
        x.setClickable(true);
        x.setFocusable(true);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ContentAdapter contentAdapter = new ContentAdapter((AppCompatActivity) x.getContext(), list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(x.getContext(), LinearLayoutManager.VERTICAL, false);

        if (list.size() > 0) {
            binding.li.setVisibility(View.GONE);
        }
        binding.listContent.setAdapter(contentAdapter);
        binding.listContent.setLayoutManager(linearLayoutManager);
        

        return x;
    }
}
