package com.example.android.mp3musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.android.mp3musicapp.Adapter.ViewPagerPlayListMusicAdapter;
import com.example.android.mp3musicapp.Fragment.Fragment_Dia_Nhac;
import com.example.android.mp3musicapp.Fragment.Fragment_Play_Music_List;
import com.example.android.mp3musicapp.Model.BaiHat;
import com.example.android.mp3musicapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {
    public static ArrayList<BaiHat> baiHatArrayList = new ArrayList<>();
    public static ViewPagerPlayListMusicAdapter viewPagerPlayListMusicAdapter;
    Toolbar toolbarPlayMusic;
    TextView tvTimeSong, tvTotalTimeSong;
    SeekBar seekBarTime;
    ImageButton imgPlay, imgNext, imgPre, imgRepeat, imgRandom;
    ViewPager viewPagerPlayMusic;
    Fragment_Dia_Nhac fragmentDiaNhac;
    Fragment_Play_Music_List fragmentPlayMusicList;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkRandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getIntentData();
        viewBinding();
        init();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlayListMusicAdapter.getItem(1) != null) {
                    if (baiHatArrayList.size() > 0) {
                        fragmentDiaNhac.playMusic(baiHatArrayList.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 100);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.ic_play);
                    fragmentDiaNhac.objectAnimator.pause();
                } else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.ic_pause);
                    fragmentDiaNhac.objectAnimator.resume();
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkRandom == true) {
                        checkRandom = false;
                        imgRepeat.setImageResource(R.drawable.ic_loop_on);
                        imgRandom.setImageResource(R.drawable.ic_shuffle);
                    }
                    imgRepeat.setImageResource(R.drawable.ic_loop_on);
                    repeat = true;
                } else {
                    imgRepeat.setImageResource(R.drawable.ic_loop);
                    repeat = false;
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkRandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgRandom.setImageResource(R.drawable.ic_shuffle_on);
                        imgRepeat.setImageResource(R.drawable.ic_loop);
                    }
                    imgRandom.setImageResource(R.drawable.ic_shuffle_on);
                    checkRandom = true;
                } else {
                    imgRandom.setImageResource(R.drawable.ic_shuffle);
                    checkRandom = false;
                }
            }
        });
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                tvTimeSong.setText(simpleDateFormat.format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiHatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (checkRandom == true) {
                        Random random = new Random();
                        int index = random.nextInt(baiHatArrayList.size());
                        position = index;
                    } else {
                        position = (position + 1) % baiHatArrayList.size();
                    }
                    new PlayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                    fragmentDiaNhac.playMusic(baiHatArrayList.get(position).getHinhBaiHat());
                    getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                    updateTime();
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                }, 2000);
            }
        });

        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiHatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (checkRandom == true) {
                        Random random = new Random();
                        int index = random.nextInt(baiHatArrayList.size());
                        position = index;
                    } else {
                        position = position - 1 < 0 ? baiHatArrayList.size() - 1 : position - 1;
                    }
                    new PlayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                    fragmentDiaNhac.playMusic(baiHatArrayList.get(position).getHinhBaiHat());
                    getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                    updateTime();
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                }, 2000);
            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarPlayMusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayMusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                baiHatArrayList.clear();
            }
        });
        toolbarPlayMusic.setTitleTextColor(Color.WHITE);
        fragmentDiaNhac = new Fragment_Dia_Nhac();
        fragmentPlayMusicList = new Fragment_Play_Music_List();
        viewPagerPlayListMusicAdapter = new ViewPagerPlayListMusicAdapter(getSupportFragmentManager());
        viewPagerPlayListMusicAdapter.addFragment(fragmentPlayMusicList);
        viewPagerPlayListMusicAdapter.addFragment(fragmentDiaNhac);
        viewPagerPlayMusic.setAdapter(viewPagerPlayListMusicAdapter);
        viewPagerPlayMusic.setCurrentItem(1);
        fragmentDiaNhac = (Fragment_Dia_Nhac) viewPagerPlayListMusicAdapter.getItem(1);
        if (baiHatArrayList.size() > 0) {
            getSupportActionBar().setTitle(baiHatArrayList.get(0).getTenBaiHat());
            new PlayMp3().execute(baiHatArrayList.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.ic_pause);
        }
    }

    private void viewBinding() {
        toolbarPlayMusic = findViewById(R.id.toolbarPlayMusic);
        tvTimeSong = findViewById(R.id.tvTimeSong);
        tvTotalTimeSong = findViewById(R.id.tvTotalTimeSong);
        seekBarTime = findViewById(R.id.seekBarSong);
        imgPlay = findViewById(R.id.imageViewButtonPlay);
        imgNext = findViewById(R.id.imageViewButtonNext);
        imgPre = findViewById(R.id.imageViewButtonPrevious);
        imgRepeat = findViewById(R.id.imageViewButtonLoop);
        imgRandom = findViewById(R.id.imageviewButtonShuffle);
        viewPagerPlayMusic = findViewById(R.id.viewPagerPlayMusic);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        baiHatArrayList.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHat baihat = intent.getParcelableExtra("cakhuc");
                baiHatArrayList.add(baihat);
            }
            if (intent.hasExtra("tatcacakhuc")) {
                ArrayList<BaiHat> tatCaCaKhuc = intent.getParcelableArrayListExtra("tatcacakhuc");
                baiHatArrayList.addAll(tatCaCaKhuc);
            }
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarTime.setMax(mediaPlayer.getDuration());
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBarTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handlerChangeSong = new Handler();
        handlerChangeSong.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (baiHatArrayList.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (checkRandom == true) {
                            Random random = new Random();
                            int index = position;
                            while(index == position){
                                index = random.nextInt(baiHatArrayList.size());
                            }
                            position = index;
                        } else {
                            position = (position + 1) % baiHatArrayList.size();
                        }
                        new PlayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                        fragmentDiaNhac.playMusic(baiHatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                    }
                    imgPre.setClickable(false);
                    imgNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgPre.setClickable(true);
                            imgNext.setClickable(true);
                        }
                    }, 2000);
                    next = false;
                    handlerChangeSong.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            updateTime();
        }
    }
}
