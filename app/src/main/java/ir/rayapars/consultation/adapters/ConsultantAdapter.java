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
import ir.rayapars.consultation.classes.ModelConsultant;
import ir.rayapars.consultation.databinding.ItemConsultantBinding;
import ir.rayapars.consultation.fragments.ConsultantDetailsFragment;

public class ConsultantAdapter extends RecyclerView.Adapter<ConsultantAdapter.ViewHolder> {

    List<ModelConsultant> list;

    AppCompatActivity context;

    public ConsultantAdapter(List<ModelConsultant> list, AppCompatActivity context) {

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.binding.tvConsultantJob.setText("مسئول فنی");
        viewHolder.binding.tvConsultantName.setText(list.get(i).name);
        viewHolder.binding.tvField.setText(list.get(i).work);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                transaction.add(R.id.frameParent, new ConsultantDetailsFragment()).addToBackStack("").commit();

            }
        });
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

}
