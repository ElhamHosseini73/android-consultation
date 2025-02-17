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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.classes.Education;
import ir.rayapars.consultation.classes.cities;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import ir.rayapars.consultation.adapters.ConsultantAdapter;
import ir.rayapars.consultation.classes.AdviserList;
import ir.rayapars.consultation.classes.Advisers;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentConsultantBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultantFragment extends Fragment {

    View v;
    int page = 1, perPage = 10;
    FragmentConsultantBinding binding;

    String[] cityStr;
    List<Education> cityList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentConsultantBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setFocusable(true);
        v.setClickable(true);

        binding.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                List<Advisers> list = new ArrayList<>();
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                ConsultantAdapter consultantAdapter = new ConsultantAdapter(list, (AppCompatActivity) v.getContext(), "");
                binding.listViewConsultant.setLayoutManager(manager);
                binding.listViewConsultant.setAdapter(consultantAdapter);

                AdviserList(cityList.get(position).val);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getCities();

        return v;
    }

    public void AdviserList(final String city) {

        final ProgressDialogFragment progressDialog;
        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<AdviserList> call = getData.AdviserList(App.KEY, page + "", perPage + "", "", "", city);
        call.enqueue(new Callback<AdviserList>() {
            @Override
            public void onResponse(Call<AdviserList> call, Response<AdviserList> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        progressDialog.dismiss();

                        List<Advisers> listt = new ArrayList<>();

                        for (int i = 0; i < response.body().advisers.size(); i++) {

                            listt.add(response.body().advisers.get(i));
                        }

                        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        ConsultantAdapter consultantAdapter = new ConsultantAdapter(listt, (AppCompatActivity) v.getContext(), city);
                        binding.listViewConsultant.setLayoutManager(manager);
                        binding.listViewConsultant.setAdapter(consultantAdapter);

                    } else {

                        Toast.makeText(v.getContext(), response.body().message + "", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                } else {

                    Toast.makeText(v.getContext(), "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<AdviserList> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(v.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void getCities() {

        final ProgressDialogFragment progressDialog;
        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<cities> call = getData.getCities(App.KEY);
        call.enqueue(new Callback<cities>() {
            @Override
            public void onResponse(Call<cities> call, Response<cities> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        progressDialog.dismiss();

                        cityList = new ArrayList<>();
                        cityStr = new String[response.body().cities.size()];

                        for (int j = 0; j < response.body().cities.size(); j++) {

                            cityList.add(response.body().cities.get(j));
                            cityStr[j] = response.body().cities.get(j).name;
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, cityStr);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.spinnerCity.setAdapter(adapter);

                    } else {

                        Toast.makeText(v.getContext(), response.body().message + "", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                } else {

                    Toast.makeText(v.getContext(), "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<cities> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(v.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
