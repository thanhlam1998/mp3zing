package com.example.android.mp3musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.android.mp3musicapp.Activity.MusicListActivity;
import com.example.android.mp3musicapp.Model.QuangCao;
import com.example.android.mp3musicapp.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.banner_item, null);

        ImageView imageBackgroundBanner = view.findViewById(R.id.imageViewBackgroundBanner);
        ImageView imgSongBanner = view.findViewById(R.id.imageViewBanner);
        TextView tvTitleSongBanner = view.findViewById(R.id.tvTitleBannerBaiHat);
        TextView tvNoiDung = view.findViewById(R.id.tvNoiDung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnh()).into(imageBackgroundBanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(imgSongBanner);
        tvTitleSongBanner.setText(arrayListBanner.get(position).getTenBaiHat());
        tvNoiDung.setText(arrayListBanner.get(position).getNoiDung());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MusicListActivity.class);
                intent.putExtra("banner", arrayListBanner.get(position));
                context.startActivity(intent);
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
