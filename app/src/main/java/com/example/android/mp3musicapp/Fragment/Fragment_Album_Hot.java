package com.example.android.mp3musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mp3musicapp.Activity.AllAlbumListActivity;
import com.example.android.mp3musicapp.Adapter.AlbumAdapter;
import com.example.android.mp3musicapp.Model.Album;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class Fragment_Album_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewAlbum;
    TextView tvXemThemAlbum;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot, container, false);
        bindingView();
        init();
        getData();
        return view;
    }

    private void init() {
        tvXemThemAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllAlbumListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindingView() {
        recyclerViewAlbum = view.findViewById(R.id.recylerViewAlbumHot);
        tvXemThemAlbum = view.findViewById(R.id.tvXemThemAlbum);
    }

    private void getData() {
        DataService service = ApiService.getService();
        Call<List<Album>> callback = service.getAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(), albumArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(HORIZONTAL);
                recyclerViewAlbum.setLayoutManager(linearLayoutManager);
                recyclerViewAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
