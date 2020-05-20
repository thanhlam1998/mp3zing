package com.example.android.mp3musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mp3musicapp.Activity.PlayMusicActivity;
import com.example.android.mp3musicapp.Model.BaiHat;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMusicAdapter extends RecyclerView.Adapter<SearchMusicAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public SearchMusicAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_music_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.tvTenCaSi.setText(baiHat.getCaSi());
        holder.tvTenBaiHatHot.setText(baiHat.getTenBaiHat());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgHinhBaiHatHot);
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenBaiHatHot, tvTenCaSi;
        ImageView imgHinhBaiHatHot, imgLuotThich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenBaiHatHot = itemView.findViewById(R.id.tvSearchMusic);
            tvTenCaSi = itemView.findViewById(R.id.tvSearchMusicSinger);
            imgHinhBaiHatHot = itemView.findViewById(R.id.imageViewSearchMusic);
            imgLuotThich = itemView.findViewById(R.id.imageViewLuotThichSearchMusic);
            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(imgLuotThich.getDrawable().getConstantState().equals(context.getDrawable(R.drawable.ic_white_love).getConstantState())){
                        imgLuotThich.setImageResource(R.drawable.ic_red_love);
                        DataService service = ApiService.getService();
                        Call<String> callback = service.updateLuotThich("1", baiHatArrayList.get(getPosition()).getIdBaiHat());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String ketqua = response.body();
                                if(ketqua.equals("Success")){
                                    Toast.makeText(context, "Đã thích bài hát", Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(context, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    } else {
                        imgLuotThich.setImageResource(R.drawable.ic_white_love);
                        DataService service = ApiService.getService();
                        Call<String> callback = service.updateLuotThich("-1", baiHatArrayList.get(getPosition()).getIdBaiHat());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String ketqua = response.body();
                                if(ketqua.equals("Success")){
                                    Toast.makeText(context, "Đã bỏ thích bài hát", Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(context, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc", baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
