package com.example.android.mp3musicapp.Service;

import com.example.android.mp3musicapp.Model.Album;
import com.example.android.mp3musicapp.Model.BaiHat;
import com.example.android.mp3musicapp.Model.ChuDe;
import com.example.android.mp3musicapp.Model.ChuDeVaTheLoai;
import com.example.android.mp3musicapp.Model.PlayList;
import com.example.android.mp3musicapp.Model.QuangCao;
import com.example.android.mp3musicapp.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("songbanner.php")
    Call<List<QuangCao>> getDataBanner();

    @GET("playlistforcurrrentpage.php")
    Call<List<PlayList>> getPlayListCurrentDay();

    @GET("chudevatheloai.php")
    Call<ChuDeVaTheLoai> getCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> getAlbumHot();

    @GET("baihatuathich.php")
    Call<List<BaiHat>> getHotSong();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getMusicListByAdvertise(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getMusicListByPlayList(@Field("id_playlist") String idplaylist);

    @GET("danhsachplaylist.php")
    Call<List<PlayList>> getAllPlayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getMusicListByCategory(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> getAllTopic();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> getCategoryByTopic(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> getAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getMusicListByAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<BaiHat>> getSearchMusicList(@Field("tukhoa") String tukhoa);
}
