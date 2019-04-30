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

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.DialogFragment.ProgressDialogFragment;
import ir.rayapars.consultation.adapters.ConsultantAdapter;
import ir.rayapars.consultation.adapters.ContentAdapter;
import ir.rayapars.consultation.classes.AdviserList;
import ir.rayapars.consultation.classes.Advisers;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.Blog;
import ir.rayapars.consultation.classes.BlogList;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.FragmentContentBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentFragment extends Fragment {

    View x;
    public String idDepartment;
    int page = 1, perPage = 10;
    ProgressDialogFragment progressDialog;
    FragmentContentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = FragmentContentBinding.inflate(getLayoutInflater());
        x = binding.getRoot();
        x.setClickable(true);
        x.setFocusable(true);

        progressDialog = new ProgressDialogFragment();
        progressDialog.show(getActivity().getSupportFragmentManager(), "");
        progressDialog.setCancelable(false);

        BlogList();

        return x;
    }

    public void BlogList() {

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<BlogList> call = getData.BlogList(App.KEY, idDepartment, page + "", perPage + "");
        call.enqueue(new Callback<BlogList>() {
            @Override
            public void onResponse(Call<BlogList> call, Response<BlogList> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        progressDialog.dismiss();

                        List<Blog> list = new ArrayList<>();

                        for (int i = 0; i < response.body().posts.size(); i++) {

                            list.add(response.body().posts.get(i));
                        }

                        ContentAdapter contentAdapter = new ContentAdapter((AppCompatActivity) x.getContext(), list);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(x.getContext(), LinearLayoutManager.VERTICAL, false);

                        if (list.size() > 0) {
                            binding.li.setVisibility(View.GONE);
                        }
                        binding.listContent.setAdapter(contentAdapter);
                        binding.listContent.setLayoutManager(linearLayoutManager);

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
            public void onFailure(Call<BlogList> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(x.getContext(), "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
