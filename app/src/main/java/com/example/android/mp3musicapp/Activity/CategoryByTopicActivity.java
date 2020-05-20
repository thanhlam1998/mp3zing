package com.example.android.mp3musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.mp3musicapp.Adapter.CategoryByTopicAdapter;
import com.example.android.mp3musicapp.Model.ChuDe;
import com.example.android.mp3musicapp.Model.TheLoai;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryByTopicActivity extends AppCompatActivity {
    ChuDe chuDe;
    RecyclerView recyclerViewCategoryByTopic;
    Toolbar toolbarCategoryByTopic;
    CategoryByTopicAdapter categoryByTopicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_by_topic);
        GetIntent();
        viewBinding();
        init();
        getData();
    }

    private void getData() {
        DataService service = ApiService.getService();
        Call<List<TheLoai>> callback = service.getCategoryByTopic(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                categoryByTopicAdapter = new CategoryByTopicAdapter(CategoryByTopicActivity.this, theLoaiArrayList);
                recyclerViewCategoryByTopic.setLayoutManager(new GridLayoutManager(CategoryByTopicActivity.this, 2));
                recyclerViewCategoryByTopic.setAdapter(categoryByTopicAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarCategoryByTopic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbarCategoryByTopic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void viewBinding() {
        recyclerViewCategoryByTopic = findViewById(R.id.recyclerViewCategoryByTopic);
        toolbarCategoryByTopic = findViewById(R.id.toolBatCategoryByTopic);
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
        }
    }
}
