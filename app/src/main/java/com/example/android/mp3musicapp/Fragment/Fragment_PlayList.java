package com.example.android.mp3musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.mp3musicapp.Activity.AllPlayListActivity;
import com.example.android.mp3musicapp.Activity.MusicListActivity;
import com.example.android.mp3musicapp.Adapter.PlayListAdapter;
import com.example.android.mp3musicapp.Model.PlayList;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;
import com.example.android.mp3musicapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment{
    View view;
    ListView lvPlayList;
    TextView tvTitlePlayList, tvXemThem;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> arrayPlayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        bindingView();
        getData();
        tvXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllPlayListActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void bindingView() {
        lvPlayList = view.findViewById(R.id.listViewPlayList);
        tvTitlePlayList = view.findViewById(R.id.tvTitlePlayList);
        tvXemThem = view.findViewById(R.id.tvMorePlayList);
    }

    private void getData() {
        DataService dataService = ApiService.getService();
        Call<List<PlayList>> callback = dataService.getPlayListCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                arrayPlayList = (ArrayList<PlayList>) response.body();
                playListAdapter = new PlayListAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayPlayList);
                lvPlayList.setAdapter(playListAdapter);
                Utils.setListViewHeightBasedOnChildren(lvPlayList);
                lvPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), MusicListActivity.class);
                        intent.putExtra("itemplaylist", arrayPlayList.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
}
