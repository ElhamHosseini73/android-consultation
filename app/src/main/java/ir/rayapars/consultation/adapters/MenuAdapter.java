package ir.rayapars.consultation.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ir.rayapars.consultation.activitys.CompletedProfileActivity;
import ir.rayapars.consultation.activitys.SplashActivity;
import ir.rayapars.consultation.classes.MenuClass;
import ir.rayapars.consultation.classes.UserInfo;
import ir.rayapars.consultation.fragments.AboutUsFragment;
import ir.rayapars.consultation.fragments.CallFragment;
import ir.rayapars.consultation.fragments.FeadBackFragment;
import ir.rayapars.consultation.fragments.InfractionsFragment;
import ir.rayapars.consultation.fragments.MyAccountFragment;
import ir.rayapars.consultation.fragments.RewardCodeFragment;
import ir.rayapars.consultation.fragments.RuleFragment;
import ir.rayapars.consultation.R;
import ir.rayapars.consultation.databinding.ItemMenuBinding;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyHolder> {

    AppCompatActivity context;
    List<MenuClass> list;
    private int position;
    DrawerLayout drawerLayout;

    TextView txtName;
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

        txtName = (TextView) holder.binding.header.findViewById(R.id.txtName);

        List<UserInfo> infoList = UserInfo.listAll(UserInfo.class);

        txtName.setText(infoList.get(0).last_name + infoList.get(0).first_name);

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

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameParent, new RuleFragment()).addToBackStack("").commit();


                } else if (list.get(position).idMenu.equals("4")) {

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameParent, new InfractionsFragment()).addToBackStack("").commit();

                } else if (list.get(position).idMenu.equals("5")) {

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameParent, new FeadBackFragment()).addToBackStack("").commit();

                } else if (list.get(position).idMenu.equals("6")) {
                } else if (list.get(position).idMenu.equals("7")) {

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameParent, new CallFragment()).addToBackStack("").commit();

                } else if (list.get(position).idMenu.equals("8")) {

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameParent, new AboutUsFragment()).addToBackStack("").commit();

                } else if (list.get(position).idMenu.equals("9")) {


                    UserInfo.deleteAll(UserInfo.class);

                    context.finish();
                    Intent intent = new Intent(context, CompletedProfileActivity.class);
                    context.startActivity(intent);

                    Toast.makeText(context, "خروج از حساب کاربری با موفقیت انجام شد", Toast.LENGTH_SHORT).show();


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

