package com.example.android.mp3musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.android.mp3musicapp.Adapter.AllAlbumAdapter;
import com.example.android.mp3musicapp.Model.Album;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumListActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllAlbum;
    Toolbar toolbarAllAlbum;
    AllAlbumAdapter allAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_album_list);
        viewBinding();
        init();
        getData();
    }

    private void getData() {
        DataService service = ApiService.getService();
        Call<List<Album>> callback = service.getAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                allAlbumAdapter = new AllAlbumAdapter(AllAlbumListActivity.this, albumArrayList);
                recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(AllAlbumListActivity.this, 2));
                recyclerViewAllAlbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarAllAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbarAllAlbum.setTitleTextColor(getResources().getColor(R.color.purple));
        toolbarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void viewBinding() {
        recyclerViewAllAlbum = findViewById(R.id.recyclerViewAllAlbum);
        toolbarAllAlbum = findViewById(R.id.toolBarAllAlbum);
    }
}
