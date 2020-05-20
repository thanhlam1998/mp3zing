package com.example.android.mp3musicapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.mp3musicapp.Model.PlayList;
import com.example.android.mp3musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<PlayList> {
    public PlayListAdapter(Context context, int resource, List<PlayList> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView tvPlayListName;
        ImageView imgBackground, imgPlayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.playlist_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvPlayListName = convertView.findViewById(R.id.tvPlayListName);
            viewHolder.imgBackground = convertView.findViewById(R.id.imageViewBackGroundPlayList);
            viewHolder.imgPlayList = convertView.findViewById(R.id.imageViewPlayList);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhNen()).into(viewHolder.imgBackground);
        Picasso.with(getContext()).load(playList.getHinhIcon()).into(viewHolder.imgPlayList);
        viewHolder.tvPlayListName.setText(playList.getTen());
        return convertView;
    }
}
