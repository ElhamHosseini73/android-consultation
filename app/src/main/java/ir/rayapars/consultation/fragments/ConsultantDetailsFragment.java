package ir.rayapars.consultation.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.DialogFragment.ProgressDialogFragment;
import ir.rayapars.consultation.R;
import ir.rayapars.consultation.adapters.ConsultantAdapter;
import ir.rayapars.consultation.adapters.EducationAdapter;
import ir.rayapars.consultation.classes.AdviserDetails;
import ir.rayapars.consultation.classes.AdviserList;
import ir.rayapars.consultation.classes.Advisers;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentConsultantDetailsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultantDetailsFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    View x;
    public String id;
    ProgressDialogFragment progressDialog;
    FragmentConsultantDetailsBinding binding;
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    String urlTlg, UrlInsta, UrlMail, urlSite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentConsultantDetailsBinding.inflate(getLayoutInflater());
        x = binding.getRoot();
        x.setFocusable(true);
        x.setClickable(true);

        binding.appbar.addOnOffsetChangedListener(this);

        startAlphaAnimation(binding.title, 0, View.INVISIBLE);

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);


        binding.imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(UrlInsta));
                    startActivity(i);
                } catch (Exception e) {
                }
            }
        });


        AdviserDetails();
        return x;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {

        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(binding.title, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(binding.title, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {

        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(binding.linearlayout, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(binding.linearlayout, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {

        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    public void AdviserDetails() {

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<AdviserDetails> call = getData.adviserDetails(App.KEY, id);
        call.enqueue(new Callback<AdviserDetails>() {
            @Override
            public void onResponse(Call<AdviserDetails> call, Response<AdviserDetails> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {


                        binding.title.setText(response.body().adviser.name);
                        binding.name.setText(response.body().adviser.name);
                        binding.job.setText(response.body().adviser.job);
                        binding.tvField.setText(response.body().adviser.text);
                        progressDialog.dismiss();

                        UrlInsta = response.body().adviser.instagram;


                        Picasso.with(x.getContext().getApplicationContext()).load(response.body().adviser.image)
                                .placeholder(R.drawable.ic_profile_gray).error(R.drawable.ic_profile_gray)
                                .into(binding.img);

                        EducationAdapter educationAdapter = new EducationAdapter((AppCompatActivity) x.getContext(), response.body().adviser.education);
                        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        binding.recyEducation.setAdapter(educationAdapter);
                        binding.recyEducation.setLayoutManager(manager);


                    } else {

                        Toast.makeText(x.getContext(), response.body().message + "", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                } else {

                    Toast.makeText(x.getContext(), "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<AdviserDetails> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(x.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
