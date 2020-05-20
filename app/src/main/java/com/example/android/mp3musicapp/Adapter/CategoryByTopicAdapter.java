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

import com.example.android.mp3musicapp.Activity.MusicListActivity;
import com.example.android.mp3musicapp.Model.ChuDe;
import com.example.android.mp3musicapp.Model.TheLoai;
import com.example.android.mp3musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryByTopicAdapter extends RecyclerView.Adapter<CategoryByTopicAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> chuDeArrayList;

    public CategoryByTopicAdapter(Context context, ArrayList<TheLoai> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_by_topic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = chuDeArrayList.get(position);
        holder.tvCategoryByTopic.setText(theLoai.getTenTheLoai());
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imageViewCategoryByTopic);
    }

    @Override
    public int getItemCount() {
        if (chuDeArrayList != null){
            return chuDeArrayList.size();
        }
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewCategoryByTopic;
        TextView tvCategoryByTopic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCategoryByTopic = itemView.findViewById(R.id.imageViewCategoryByTopic);
            tvCategoryByTopic = itemView.findViewById(R.id.tvCategoryByTopic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MusicListActivity.class);
                    intent.putExtra("idtheloai", chuDeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
