package ir.rayapars.consultation.adapters;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ir.rayapars.consultation.classes.Education;
import ir.rayapars.consultation.databinding.ItemEducationBinding;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyHolder> {

    AppCompatActivity activity;
    List<Education> education;

    public EducationAdapter(AppCompatActivity activity, List<Education> education) {

        this.activity = activity;
        this.education = education;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemEducationBinding binding = ItemEducationBinding.inflate(inflater, viewGroup, false);
        return new EducationAdapter.MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.binding.name.setText(education.get(i).name);
        myHolder.binding.value.setText(":" + education.get(i).val);
    }

    @Override
    public int getItemCount() {
        return education.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ItemEducationBinding binding;

        public MyHolder(ItemEducationBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
