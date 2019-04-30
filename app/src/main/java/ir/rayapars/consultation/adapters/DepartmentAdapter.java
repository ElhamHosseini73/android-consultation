package ir.rayapars.consultation.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.BlogCat;
import ir.rayapars.consultation.databinding.ItemDepartmentBinding;
import ir.rayapars.consultation.fragments.ContentFragment;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyHolder> {

    List<BlogCat> list;
    AppCompatActivity activity;

    public DepartmentAdapter(List<BlogCat> list, AppCompatActivity activity) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater =LayoutInflater.from(viewGroup.getContext());
       ItemDepartmentBinding binding = ItemDepartmentBinding.inflate(inflater,viewGroup,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {

        myHolder.binding.title.setText(list.get(i).title);

        Picasso.with(activity.getApplicationContext()).load(list.get(i).image)
                .placeholder(R.drawable.ic_profile_gray).error(R.drawable.ic_profile_gray)
                .into(myHolder.binding.img);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                ContentFragment fragment = new ContentFragment();
                fragment.idDepartment = list.get(i).id;
                transaction.add(R.id.frameMain, fragment).addToBackStack("").commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ItemDepartmentBinding binding;

        public MyHolder(ItemDepartmentBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
