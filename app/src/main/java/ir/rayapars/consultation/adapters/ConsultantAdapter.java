package ir.rayapars.consultation.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.rayapars.consultation.dialogFragment.ProgressDialogFragment;
import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.AdviserList;
import ir.rayapars.consultation.classes.Advisers;
import ir.rayapars.consultation.classes.App;
import ir.rayapars.consultation.classes.RetrofitClient;
import ir.rayapars.consultation.databinding.ItemConsultantBinding;
import ir.rayapars.consultation.fragments.ConsultantDetailsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultantAdapter extends RecyclerView.Adapter<ConsultantAdapter.ViewHolder> {

    List<Advisers> list;
    AppCompatActivity context;
    int page = 1, perPage = 10;
    ProgressDialogFragment progressDialog;

    public ConsultantAdapter(List<Advisers> list, AppCompatActivity context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemConsultantBinding binding = ItemConsultantBinding.inflate(inflater, viewGroup, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.binding.tvConsultantJob.setText(list.get(i).job);
        viewHolder.binding.tvConsultantName.setText(list.get(i).name);
        viewHolder.binding.tvField.setText(list.get(i).groupName);
        viewHolder.binding.consultantPic.setImageURI(Uri.parse(list.get(i).image));

        Picasso.with(context.getApplicationContext()).load(list.get(i).image)
                .placeholder(R.drawable.ic_profile_gray).error(R.drawable.ic_profile_gray)
                .into(viewHolder.binding.consultantPic);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                ConsultantDetailsFragment consultantDetailsFragment = new ConsultantDetailsFragment();
                consultantDetailsFragment.id = list.get(i).id;
                transaction.add(R.id.frameParent, consultantDetailsFragment).addToBackStack("").commit();

            }
        });

        if (i + 1 == page * perPage) {

            progressDialog = new ProgressDialogFragment();
            progressDialog.show(context.getSupportFragmentManager(), "");
            progressDialog.setCancelable(false);

            page++;

            AdviserList();

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemConsultantBinding binding;

        public ViewHolder(@NonNull ItemConsultantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    public void AdviserList() {

        RetrofitClient getData = App.getRetrofit().create(RetrofitClient.class);
        Call<AdviserList> call = getData.AdviserList(App.KEY, page + "", perPage + "", "","");
        call.enqueue(new Callback<AdviserList>() {
            @Override
            public void onResponse(Call<AdviserList> call, Response<AdviserList> response) {

                if (response.isSuccessful()) {

                    if (response.body().status.equals("1")) {

                        progressDialog.dismiss();

                        progressDialog.dismiss();

                        list.addAll(response.body().advisers);
                        notifyItemRangeChanged(list.size() - perPage, list.size());

                    } else {

                        Toast.makeText(context, "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                } else {

                    Toast.makeText(context, "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<AdviserList> call, Throwable t) {

                Toast.makeText(context, "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}
