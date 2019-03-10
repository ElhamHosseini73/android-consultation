package ir.rayapars.consultation.Adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ir.rayapars.consultation.Classes.MenuClass;
import ir.rayapars.consultation.Fragments.MyAccountFragment;
import ir.rayapars.consultation.Fragments.RewardCodeFragment;
import ir.rayapars.consultation.R;
import ir.rayapars.consultation.databinding.ItemMenuBinding;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyHolder> {

    AppCompatActivity context;
    List<MenuClass> list;
    private int position;
    DrawerLayout drawerLayout;

    boolean checkRead = false;
    private static final int REED_REQUEST_CODE = 300;

    public MenuAdapter(AppCompatActivity context, List<MenuClass> list, DrawerLayout drawerLayout) {

        this.context = context;
        this.list = list;
        this.drawerLayout = drawerLayout;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMenuBinding itemMenuBinding = ItemMenuBinding.inflate(layoutInflater, parent, false);

        return new MenuAdapter.MyHolder(itemMenuBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

        holder.binding.titleItemMenu.setText(list.get(position).titleMenu + "");
        holder.binding.imgItemMenu.setImageDrawable(holder.itemView.getResources().getDrawable(list.get(position).imgMenu));

        if (position == 0) {

            holder.binding.header.setVisibility(View.VISIBLE);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).idMenu.equals("1")) {

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameMain, new MyAccountFragment()).addToBackStack("").commit();

                } else if (list.get(position).idMenu.equals("2")) {

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameParent, new RewardCodeFragment()).addToBackStack("").commit();


                } else if (list.get(position).idMenu.equals("3")) {
                } else if (list.get(position).idMenu.equals("4")) {
                } else if (list.get(position).idMenu.equals("5")) {
                } else if (list.get(position).idMenu.equals("6")) {
                } else if (list.get(position).idMenu.equals("7")) {
                } else if (list.get(position).idMenu.equals("8")) {
                } else if (list.get(position).idMenu.equals("9")) {
                }


                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {

                    drawerLayout.closeDrawer(Gravity.RIGHT);

                } else {

                    drawerLayout.openDrawer(Gravity.RIGHT);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ItemMenuBinding binding;

        public MyHolder(ItemMenuBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }


}

