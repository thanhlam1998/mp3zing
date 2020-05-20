package com.example.android.mp3musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mp3musicapp.Activity.PlayMusicActivity;
import com.example.android.mp3musicapp.Adapter.PlayMusicListAdapter;
import com.example.android.mp3musicapp.R;

public class Fragment_Play_Music_List extends Fragment {
    View view;
    RecyclerView recyclerViewPlayMusicList;
    PlayMusicListAdapter playMusicListAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_music_list, container, false);
        recyclerViewPlayMusicList = view.findViewById(R.id.recyclerViewPlayMusicList);
        if(PlayMusicActivity.baiHatArrayList.size() > 0){
            playMusicListAdapter = new PlayMusicListAdapter(getActivity(), PlayMusicActivity.baiHatArrayList);
            recyclerViewPlayMusicList.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayMusicList.setAdapter(playMusicListAdapter);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
