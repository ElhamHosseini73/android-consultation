package ir.rayapars.consultation.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.databinding.ItemContentsBinding;
import ir.rayapars.consultation.fragments.DetailContentsFragment;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyHolder> {

    AppCompatActivity activity;
    List<String> list;

    public ContentAdapter(AppCompatActivity activity, List<String> list) {

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
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                transaction.add(R.id.frameMain, new DetailContentsFragment()).addToBackStack("").commit();

            }
        });
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
}
