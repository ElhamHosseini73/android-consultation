package ir.rayapars.consultation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ir.rayapars.consultation.classes.AboutMessage;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentRulesBinding;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RuleFragment extends Fragment {

    View v;
    ProgressDialogFragment progressDialog;
    FragmentRulesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentRulesBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setFocusable(true);
        v.setClickable(true);

        binding.toolbar.imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().popBackStack();
            }
        });

        binding.toolbar.txtPageName.setText("شرایط و قوانین");

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        About();

        return v;
    }

    public void About() {

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<AboutMessage> call = getData.aboutUs(App.KEY, "1");

        call.enqueue(new Callback<AboutMessage>() {

            @Override
            public void onResponse(Call<AboutMessage> call, Response<AboutMessage> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        binding.txtRule.setText(response.body().about.terms);
                        progressDialog.dismiss();

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
            public void onFailure(Call<AboutMessage> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(v.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
