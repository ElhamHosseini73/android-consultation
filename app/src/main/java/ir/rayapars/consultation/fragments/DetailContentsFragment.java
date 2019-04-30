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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import ir.rayapars.consultation.DialogFragment.ProgressDialogFragment;
import ir.rayapars.consultation.R;
import ir.rayapars.consultation.adapters.EducationAdapter;
import ir.rayapars.consultation.classes.AdviserDetails;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.PostDetails;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentDetailsContentsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailContentsFragment extends Fragment {

    View x;
    public String idContent;
    ProgressDialogFragment progressDialog;
    FragmentDetailsContentsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentDetailsContentsBinding.inflate(getLayoutInflater());
        x = binding.getRoot();
        x.setFocusable(true);
        x.setClickable(true);

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        PostDetails();

        return x;
    }

    public void PostDetails() {

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<PostDetails> call = getData.postDetails(App.KEY, idContent);
        call.enqueue(new Callback<PostDetails>() {
            @Override
            public void onResponse(Call<PostDetails> call, Response<PostDetails> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        binding.tvTitle.setText(response.body().post.title);
                        binding.tvtxt.setText(response.body().post.full_text);

                        progressDialog.dismiss();

                        Picasso.with(x.getContext().getApplicationContext()).load(response.body().post.image)
                                .placeholder(R.drawable.ic_profile_gray).error(R.drawable.ic_profile_gray)
                                .into(binding.image);

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
            public void onFailure(Call<PostDetails> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(x.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });


    }


}
