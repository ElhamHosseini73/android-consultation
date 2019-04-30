package ir.rayapars.consultation.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.rayapars.consultation.DialogFragment.ProgressDialogFragment;
import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.Blog;
import ir.rayapars.consultation.classes.BlogList;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.ItemContentsBinding;
import ir.rayapars.consultation.fragments.DetailContentsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyHolder> {

    AppCompatActivity activity;
    List<Blog> list;
    int page = 1, perPage = 10;
    ProgressDialogFragment progressDialog;

    public ContentAdapter(AppCompatActivity activity, List<Blog> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemContentsBinding binding = ItemContentsBinding.inflate(layoutInflater, viewGroup, false);
        return new ContentAdapter.MyHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {

        myHolder.binding.tvTitle.setText(list.get(i).title);
        myHolder.binding.tvDesc.setText(list.get(i).short_text);
        myHolder.binding.tvAuthor.setText(list.get(i).author);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                DetailContentsFragment fragment = new DetailContentsFragment();
                fragment.idContent = list.get(i).id;
                transaction.add(R.id.frameMain, fragment).addToBackStack("").commit();

            }
        });

        Picasso.with(activity.getApplicationContext()).load(list.get(i).image)
                .placeholder(R.drawable.ic_profile_gray).error(R.drawable.ic_profile_gray)
                .into(myHolder.binding.itemImage);


        if (i + 1 == page * perPage) {

            progressDialog = new ProgressDialogFragment();
            progressDialog.show(activity.getSupportFragmentManager(), "");
            progressDialog.setCancelable(false);

            page++;

            BlogList(list.get(i).id);

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ItemContentsBinding binding;

        public MyHolder(@NonNull ItemContentsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }

    public void BlogList(String idDepartment) {

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<BlogList> call = getData.BlogList(App.KEY, idDepartment, page + "", perPage + "");
        call.enqueue(new Callback<BlogList>() {
            @Override
            public void onResponse(Call<BlogList> call, Response<BlogList> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        progressDialog.dismiss();

                        list.addAll(response.body().posts);
                        notifyItemRangeChanged(list.size() - perPage, list.size());


                    } else {

                        Toast.makeText(activity, response.body().message + "", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                } else {

                    Toast.makeText(activity, "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<BlogList> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(activity, "No Connect!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
