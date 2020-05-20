package com.example.android.mp3musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.android.mp3musicapp.Adapter.AllTopicAdapter;
import com.example.android.mp3musicapp.Model.ChuDe;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTopicActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllCateGory;
    Toolbar toolBarAllCategory;
    AllTopicAdapter allTopicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_topic);
        viewBinding();
        init();
        getData();
    }

    private void getData() {
        DataService service = ApiService.getService();
        Call<List<ChuDe>> callback = service.getAllTopic();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
                allTopicAdapter = new AllTopicAdapter(AllTopicActivity.this, chuDeArrayList);
                recyclerViewAllCateGory.setLayoutManager(new LinearLayoutManager(AllTopicActivity.this));
                recyclerViewAllCateGory.setAdapter(allTopicAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolBarAllCategory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");
        toolBarAllCategory.setTitleTextColor(getResources().getColor(R.color.purple));
        toolBarAllCategory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void viewBinding() {
        recyclerViewAllCateGory = findViewById(R.id.recyclerViewAllCategory);
        toolBarAllCategory = findViewById(R.id.toolBarAllCategory);
    }
}
