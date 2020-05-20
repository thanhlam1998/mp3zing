package com.example.android.mp3musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.android.mp3musicapp.Adapter.AllPlayListAdapter;
import com.example.android.mp3musicapp.Model.PlayList;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPlayListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewAllPlayList;
    AllPlayListAdapter allPlayListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_play_list);
        bindingView();
        init();
        getData();
    }

    private void getData() {
        DataService service = ApiService.getService();
        Call<List<PlayList>> callback = service.getAllPlayList();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> playListArrayList = (ArrayList<PlayList>) response.body();
                allPlayListAdapter = new AllPlayListAdapter(AllPlayListActivity.this, playListArrayList);
                recyclerViewAllPlayList.setLayoutManager(new GridLayoutManager(AllPlayListActivity.this, 2));
                recyclerViewAllPlayList.setAdapter(allPlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.purple));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void bindingView() {
        toolbar = findViewById(R.id.toolBarAllPlayList);
        recyclerViewAllPlayList = findViewById(R.id.recyclerViewAllPlayList);
    }
}
