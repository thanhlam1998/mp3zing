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
import com.example.android.mp3musicapp.Model.Album;
import com.example.android.mp3musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumArrayList;

    public AllAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.tvAlbum.setText(album.getTenAlbum());
        holder.tvAlbumSinger.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imageViewAllAlbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewAllAlbum;
        TextView tvAlbum, tvAlbumSinger;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAllAlbum = itemView.findViewById(R.id.imageViewAllAlbum);
            tvAlbum = itemView.findViewById(R.id.tvAllAlbum);
            tvAlbumSinger = itemView.findViewById(R.id.tvAllAlbumSinger);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MusicListActivity.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
