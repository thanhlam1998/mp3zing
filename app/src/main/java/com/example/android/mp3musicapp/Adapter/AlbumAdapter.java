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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.tvTenAlbum.setText(album.getTenAlbum());
        holder.tvCaSiAlbum.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhAlbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhAlbum;
        TextView tvTenAlbum, tvCaSiAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAlbum = itemView.findViewById(R.id.imageviewalbum);
            tvTenAlbum = itemView.findViewById(R.id.tvTenAlbum);
            tvCaSiAlbum = itemView.findViewById(R.id.tvTenCaSiAlbum);
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
