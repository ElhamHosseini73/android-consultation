package ir.rayapars.consultation.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.AboutMessage;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.classes.cities;
import ir.rayapars.consultation.databinding.FragmentRewardCodeBinding;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardCodeFragment extends Fragment {

    View v;
    ProgressDialogFragment progressDialog;
    String mail, telegram, insta, app;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentRewardCodeBinding binding = FragmentRewardCodeBinding.inflate(getLayoutInflater());

        v = binding.getRoot();
        v.setFocusable(true);
        v.setClickable(true);

        binding.toolbar.txtPageName.setText("دعوت دوستان");
        binding.toolbar.imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().popBackStack();

            }
        });

        binding.imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(insta));
                    startActivity(i);
                } catch (Exception e) {
                }

            }
        });

        binding.imgTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(telegram));
                    startActivity(i);
                } catch (Exception e) {
                }
            }
        });

        binding.imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "روان پژوه");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "" + app);
                    startActivity(Intent.createChooser(emailIntent, ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        binding.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, app);
                    sendIntent.setType("text/plain");
                    getContext().startActivity(Intent.createChooser(sendIntent, getContext().getResources().getText(R.string.app_name)));

                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                }
            }
        });

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        About();

        return v;

    }

    public void About() {

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<AboutMessage> call = getData.aboutUs(App.KEY, "", "");

        call.enqueue(new Callback<AboutMessage>() {

            @Override
            public void onResponse(Call<AboutMessage> call, Response<AboutMessage> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        try {
                            mail = response.body().about.email;
                            telegram = response.body().about.telegram;
                            insta = response.body().about.instagram;
                            app = response.body().about.app;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

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
