package com.example.android.mp3musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mp3musicapp.Activity.CategoryByTopicActivity;
import com.example.android.mp3musicapp.Model.ChuDe;
import com.example.android.mp3musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllTopicAdapter extends RecyclerView.Adapter<AllTopicAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> chuDeArrayList;

    public AllTopicAdapter(Context context, ArrayList<ChuDe> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_topic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = chuDeArrayList.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imageViewAllCategory);
        holder.tvAllCategory.setText(chuDe.getTenChuDe());
    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewAllCategory;
        TextView tvAllCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAllCategory = itemView.findViewById(R.id.imageViewAllTopic);
            tvAllCategory = itemView.findViewById(R.id.tvAllTopic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CategoryByTopicActivity.class);
                    intent.putExtra("chude", chuDeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
