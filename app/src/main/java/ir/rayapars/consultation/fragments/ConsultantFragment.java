package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.adapters.ConsultantAdapter;
import ir.rayapars.consultation.classes.ModelConsultant;
import ir.rayapars.consultation.databinding.FragmentConsultantBinding;

public class ConsultantFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentConsultantBinding binding = FragmentConsultantBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setFocusable(true);
        v.setClickable(true);

        binding.listViewConsultant.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ModelConsultant> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            ModelConsultant consultant = new ModelConsultant();

            consultant.name = "دکتر الی حسینی";
            consultant.work = "روان درمانگر تحلیلی";
            list.add(consultant);
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ConsultantAdapter consultantAdapter = new ConsultantAdapter(list, v.getContext());
        binding.listViewConsultant.setLayoutManager(manager);
        binding.listViewConsultant.setAdapter(consultantAdapter);

        return v;
    }
}
