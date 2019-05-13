package ir.rayapars.consultation.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;
import java.util.List;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.adapters.ClinicAdapter;
import ir.rayapars.consultation.classes.AboutMessage;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.classes.Slider;
import ir.rayapars.consultation.databinding.FragmentClinicBinding;
import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClinicFragment extends Fragment implements BaseSliderView.OnSliderClickListener {

    FragmentClinicBinding binding;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentClinicBinding.inflate(getLayoutInflater());
        v = binding.getRoot();
        v.setFocusable(true);
        v.setClickable(true);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1", R.drawable.slider);
        file_maps.put("2", R.drawable.slider1);
        file_maps.put("3", R.drawable.slider2);

        for (String name : file_maps.keySet()) {

            TextSliderView textSliderView = new TextSliderView(getContext());

            textSliderView

                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            binding.slider.addSlider(textSliderView);

        }

        binding.slider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        binding.slider.setCustomAnimation(new DescriptionAnimation());
        binding.slider.setDuration(4000);

        ClinicAdapter clinicAdapter = new ClinicAdapter(v.getContext(), getFragmentManager());
        binding.viewpager.setAdapter(clinicAdapter);

        binding.tabs.setupWithViewPager(binding.viewpager);

        slider();

        return v;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    public void slider() {

        final ProgressDialogFragment progressDialog;

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<List<Slider>> call = getData.slider(App.KEY);

        call.enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(Call<List<Slider>> call, Response<List<Slider>> response) {

                if (response.isSuccessful()) {

                    try {

                        progressDialog.dismiss();

                    } catch (Exception e) {
                    }

                    for (int k = 0; k < response.body().size(); k++) {

                        final String link = response.body().get(k).getUrl();

                        DefaultSliderView defaultSliderView = new DefaultSliderView(getContext());
                        defaultSliderView.setScaleType(BaseSliderView.ScaleType.Fit)
                                .image(response.body().get(k).getSrc())
                                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {

                                        try {
                                            Intent i = new Intent(Intent.ACTION_VIEW);
                                            i.setData(Uri.parse(link));
                                            startActivity(i);
                                        } catch (Exception e) {

                                        }
                                    }
                                });

                        binding.slider.addSlider(defaultSliderView);
                    }

                } else {

                    Toast.makeText(getContext(), "خطا در ارتباط با سرویس", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<Slider>> call, Throwable t) {

                Toast.makeText(getContext(), "No Conect", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
