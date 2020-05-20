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
import com.example.android.mp3musicapp.Model.PlayList;
import com.example.android.mp3musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllPlayListAdapter extends RecyclerView.Adapter<AllPlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<PlayList> playListArrayList;

    public AllPlayListAdapter(Context context, ArrayList<PlayList> playListArrayList) {
        this.context = context;
        this.playListArrayList = playListArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_play_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = playListArrayList.get(position);
        holder.tvTenPlayList.setText(playList.getTen());
        Picasso.with(context).load(playList.getHinhNen()).into(holder.imgHinhNen);
    }

    @Override
    public int getItemCount() {
        return playListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhNen;
        TextView tvTenPlayList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhNen = itemView.findViewById(R.id.imageViewAllPlayList);
            tvTenPlayList = itemView.findViewById(R.id.tvAllPlayList);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MusicListActivity.class);
                    intent.putExtra("itemplaylist", playListArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
