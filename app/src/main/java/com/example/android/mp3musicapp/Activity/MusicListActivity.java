package com.example.android.mp3musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.mp3musicapp.Adapter.MusicListAdapter;
import com.example.android.mp3musicapp.Model.Album;
import com.example.android.mp3musicapp.Model.BaiHat;
import com.example.android.mp3musicapp.Model.PlayList;
import com.example.android.mp3musicapp.Model.QuangCao;
import com.example.android.mp3musicapp.Model.TheLoai;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicListActivity extends AppCompatActivity {
    QuangCao quangCao;
    PlayList playList;
    TheLoai theLoai;
    Album album;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewMusicList;
    FloatingActionButton floatingActionButton;
    ImageView imageViewMusicList;
    ArrayList<BaiHat> baiHatArrayList;
    MusicListAdapter musicListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntentReceiver();
        viewBinding();
        init();
        if(quangCao != null && !quangCao.getTenBaiHat().equals("")){
            setValueInView(quangCao.getTenBaiHat(), quangCao.getHinhBaiHat());
            getDataQuangCao(quangCao.getIdQuangCao());
        }
        if(playList != null && !playList.getTen().equals("")){
            setValueInView(playList.getTen(), playList.getHinhNen());
            getDataPlayList(playList.getIdPlayList());
        }
        if(theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getIdTheLoai());
        }
        if(album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            getDataAlbum(album.getIdAlbum());
        }
    }

    private void getDataAlbum(String idalbum) {
        DataService service = ApiService.getService();
        Call<List<BaiHat>> callback = service.getMusicListByAlbum(idalbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                musicListAdapter = new MusicListAdapter(MusicListActivity.this, baiHatArrayList);
                recyclerViewMusicList.setLayoutManager(new LinearLayoutManager(MusicListActivity.this));
                recyclerViewMusicList.setAdapter(musicListAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataTheLoai(String idTheLoai) {
        DataService service = ApiService.getService();
        Call<List<BaiHat>> callback = service.getMusicListByCategory(idTheLoai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                musicListAdapter = new MusicListAdapter(MusicListActivity.this, baiHatArrayList);
                recyclerViewMusicList.setLayoutManager(new LinearLayoutManager(MusicListActivity.this));
                recyclerViewMusicList.setAdapter(musicListAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlayList(String idPlayList) {
        DataService service = ApiService.getService();
        Call<List<BaiHat>> callback = service.getMusicListByPlayList(idPlayList);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                musicListAdapter = new MusicListAdapter(MusicListActivity.this, baiHatArrayList);
                recyclerViewMusicList.setLayoutManager(new LinearLayoutManager(MusicListActivity.this));
                recyclerViewMusicList.setAdapter(musicListAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imageViewMusicList);
    }

    private void getDataQuangCao(String idQuangCao) {
        DataService service = ApiService.getService();
        Call<List<BaiHat>> callback = service.getMusicListByAdvertise(idQuangCao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                musicListAdapter = new MusicListAdapter(MusicListActivity.this, baiHatArrayList);
                recyclerViewMusicList.setLayoutManager(new LinearLayoutManager(MusicListActivity.this));
                recyclerViewMusicList.setAdapter(musicListAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void viewBinding() {
        coordinatorLayout = findViewById(R.id.coordinatorMusicList);
        collapsingToolbarLayout = findViewById(R.id.collapseToolBar);
        toolbar = findViewById(R.id.toolBarMusicList);
        recyclerViewMusicList = findViewById(R.id.recyclerViewMusicList);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imageViewMusicList = findViewById(R.id.imageViewMusicList);
    }

    private void DataIntentReceiver() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
            }
            if(intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicListActivity.this, PlayMusicActivity.class);
                intent.putExtra("tatcacakhuc", baiHatArrayList);
                startActivity(intent);
            }
        });
    }
}
