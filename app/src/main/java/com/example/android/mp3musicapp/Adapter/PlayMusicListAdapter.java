package com.example.android.mp3musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mp3musicapp.Model.BaiHat;
import com.example.android.mp3musicapp.R;

import java.util.ArrayList;

public class PlayMusicListAdapter extends RecyclerView.Adapter<PlayMusicListAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public PlayMusicListAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.play_music_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.tvSinger.setText(baiHat.getCaSi());
        holder.tvSongName.setText(baiHat.getTenBaiHat());
        holder.tvPlayMusicListIndex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvPlayMusicListIndex, tvSongName, tvSinger;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlayMusicListIndex = itemView.findViewById(R.id.tvPlayMusicListIndex);
            tvSongName = itemView.findViewById(R.id.tvPlayMusicList_SongName);
            tvSinger = itemView.findViewById(R.id.tvPlayMusicList_Singer);
        }
    }
}
