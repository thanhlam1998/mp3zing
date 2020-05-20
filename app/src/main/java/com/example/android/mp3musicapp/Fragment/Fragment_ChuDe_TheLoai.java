package com.example.android.mp3musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.android.mp3musicapp.Activity.AllTopicActivity;
import com.example.android.mp3musicapp.Activity.CategoryByTopicActivity;
import com.example.android.mp3musicapp.Activity.MusicListActivity;
import com.example.android.mp3musicapp.Model.ChuDe;
import com.example.android.mp3musicapp.Model.ChuDeVaTheLoai;
import com.example.android.mp3musicapp.Model.TheLoai;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tvXemThemChuDeTheLoai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chu_de_the_loai, container, false);
        bindingView();
        init();
        getData();
        return view;
    }

    private void init() {
        tvXemThemChuDeTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllTopicActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindingView() {
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        tvXemThemChuDeTheLoai = view.findViewById(R.id.tvXemThemChuDeTheLoai);
    }

    private void getData() {
        DataService service = ApiService.getService();
        Call<ChuDeVaTheLoai> call = service.getCategoryMusic();
        call.enqueue(new Callback<ChuDeVaTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeVaTheLoai> call, Response<ChuDeVaTheLoai> response) {
                ChuDeVaTheLoai chuDeVaTheLoai = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chuDeVaTheLoai.getChuDe());
                theLoaiArrayList.addAll(chuDeVaTheLoai.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(640, 300);
                layout.setMargins(10,20,10, 30);
                for(int i = 0; i < chuDeArrayList.size(); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chuDeArrayList.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int currentPosition = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), CategoryByTopicActivity.class);
                            intent.putExtra("chude", chuDeArrayList.get(currentPosition));
                            startActivity(intent);
                        }
                    });
                }

                for(int j = 0; j < theLoaiArrayList.size(); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theLoaiArrayList.get(j).getHinhTheLoai() != null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int currentPosition = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), MusicListActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(currentPosition));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDeVaTheLoai> call, Throwable t) {

            }
        });
    }
}
