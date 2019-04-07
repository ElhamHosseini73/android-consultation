package ir.rayapars.consultation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.rayapars.consultation.R;
import ir.rayapars.consultation.classes.ItemInfiniteCycleViewPager;
import ir.rayapars.consultation.fragments.ConsultantFragment;
import ir.rayapars.consultation.fragments.DepartmentFragment;
import ir.rayapars.consultation.fragments.EditMyAccountFragment;
import ir.rayapars.consultation.fragments.RatingFragment;

public class InfiniteCycleViewPagerAdapter extends PagerAdapter {

    AppCompatActivity context;
    ItemInfiniteCycleViewPager[] item;


    public InfiniteCycleViewPagerAdapter(AppCompatActivity context, ItemInfiniteCycleViewPager[] item) {

        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_infinite_cycle_view_pager, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_item);
        TextView txt = (TextView) view.findViewById(R.id.txt_item);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        imageView.setImageResource(item[position].getImg());
        txt.setText(item[position].getTitle());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameMain, new DepartmentFragment()).addToBackStack("").commit();

                } else if (position == 1) {

                    Toast.makeText(context, "==", Toast.LENGTH_SHORT).show();

                } else if (position == 2) {

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameMain, new RatingFragment()).addToBackStack("").commit();

                } else if (position == 3) {

                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.add(R.id.frameMain, new ConsultantFragment()).addToBackStack("").commit();

                }
            }
        });

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}


