package com.example.a10609516.businessbrochure;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.example.a10609516.businessbrochure.Tools.FullVideoView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class VideoActivity extends AppCompatActivity {

    private FrameLayout video_flt;
    private LinearLayout video_llt, video01_llt, video02_llt, video03_llt, video04_llt;
    private ImageView video_bk, video01_imv, video02_imv, video03_imv, video04_imv;
    private FullVideoView fullVideoView;
    String vidAddress = "http://220.133.80.146/WQP_Video/bwt01.mp4";

    private Button last_btn, next_btn;
    private FloatingActionMenu logo_fab;
    private FloatingActionButton home_fab, bwt_fab, aqu_fab, bin_fab;

    //配合多解析度畫面，會調整Menu的高度
    private int WIDTH, HEIGHT;
    //讀取手機裝置資訊元件
    private DisplayMetrics mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //取消NotificationBar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //取消ActionBar
        getSupportActionBar().hide();
        //動態取得 View 物件
        InitFunction();
        //獲取手機裝置資訊
        getWindowSize();
        //設置View圖片
        addImageView();
    }

    /**
     * 動態取得 View 物件
     */
    private void InitFunction() {
        video_flt = (FrameLayout) findViewById(R.id.video_flt);
        video_bk = (ImageView) findViewById(R.id.video_bk);
        video_llt = (LinearLayout) findViewById(R.id.video_llt);
        video01_llt = (LinearLayout) findViewById(R.id.video01_llt);
        video02_llt = (LinearLayout) findViewById(R.id.video02_llt);
        video03_llt = (LinearLayout) findViewById(R.id.video03_llt);
        video04_llt = (LinearLayout) findViewById(R.id.video04_llt);
        /*video01_imv = (ImageView) findViewById(R.id.video01_imv);
        video02_imv = (ImageView) findViewById(R.id.video02_imv);
        video03_imv = (ImageView) findViewById(R.id.video03_imv);
        video04_imv = (ImageView) findViewById(R.id.video04_imv);*/
        last_btn = (Button) findViewById(R.id.last_btn);
        next_btn = (Button) findViewById(R.id.next_btn);
        logo_fab = (FloatingActionMenu) findViewById(R.id.logo_fab);
        home_fab = (FloatingActionButton) findViewById(R.id.home_fab);
        bwt_fab = (FloatingActionButton) findViewById(R.id.bwt_fab);

        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(VideoActivity.this, GlobalActivity.class);
                startActivity(next_intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(VideoActivity.this, SportsSponsorshipActivity.class);
                startActivity(next_intent);
            }
        });

        home_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(VideoActivity.this, HomepageActivity.class);
                startActivity(bwt_intent);
            }
        });

        bwt_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(VideoActivity.this, CutscenesActivity.class);
                startActivity(bwt_intent);
            }
        });
    }

    /**
     * 獲取手機裝置資訊
     */
    private void getWindowSize() {
        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);
        //圖片的寬度
        WIDTH = (int) ((float) mPhone.widthPixels);
        //圖片的高度
        HEIGHT = (int) ((float) mPhone.heightPixels);
    }

    /**
     * 設置View圖片
     */
    private void addImageView() {
        //設置fab menu圖片
        logo_fab.getMenuIconView().setImageResource(R.drawable.wqp_logo);
        //把view設置在最上層
        logo_fab.bringToFront();
        next_btn.bringToFront();

        //設置VideoActivity背景圖片
        video_bk.setBackgroundResource(R.drawable.video_bk);
        FrameLayout.LayoutParams bk_pm = new FrameLayout.LayoutParams(WIDTH, HEIGHT);
        video_bk.setLayoutParams(bk_pm);

        //動態新增video播放圖
        video01_imv = new ImageView(VideoActivity.this);
        video02_imv = new ImageView(VideoActivity.this);
        video03_imv = new ImageView(VideoActivity.this);
        video04_imv = new ImageView(VideoActivity.this);

        video01_imv.setBackgroundResource(R.drawable.video01);
        video02_imv.setBackgroundResource(R.drawable.video02);
        video03_imv.setBackgroundResource(R.drawable.video03);
        video04_imv.setBackgroundResource(R.drawable.video04);

        video01_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        video02_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        video03_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        video04_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        LinearLayout.LayoutParams video_pm = new LinearLayout.LayoutParams(WIDTH / 4, HEIGHT / 4);
        video01_imv.setLayoutParams(video_pm);
        video02_imv.setLayoutParams(video_pm);
        video03_imv.setLayoutParams(video_pm);
        video04_imv.setLayoutParams(video_pm);

        video01_llt.addView(video01_imv);
        video02_llt.addView(video02_imv);
        video03_llt.addView(video03_imv);
        video04_llt.addView(video04_imv);

        video01_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放影片時隱藏logo_fab & next_btn
                logo_fab.setVisibility(View.GONE);
                next_btn.setVisibility(View.GONE);
                //動態新增fullVideoView
                fullVideoView = new FullVideoView(VideoActivity.this);
                fullVideoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory() + "/Movies//bwt01.mp4"));
                //videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bwt01));
                fullVideoView.setMediaController(new MediaController(VideoActivity.this));
                fullVideoView.requestFocus();
                fullVideoView.start();
                fullVideoView.setTag(1);

                video_flt.addView(fullVideoView);
                fullVideoView.bringToFront();

                //當影片撥放時，video_imv無法點擊
                video01_imv.setClickable(false);
                video02_imv.setClickable(false);
                video03_imv.setClickable(false);
                video04_imv.setClickable(false);

                fullVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        video_flt.removeView(fullVideoView);
                        fullVideoView.setTag(null);
                        logo_fab.setVisibility(View.VISIBLE);
                        next_btn.setVisibility(View.VISIBLE);
                        //當影片撥放時，video_imv無法點擊
                        video01_imv.setClickable(true);
                        video02_imv.setClickable(true);
                        video03_imv.setClickable(true);
                        video04_imv.setClickable(true);
                    }
                });
            }
        });

        video02_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放影片時隱藏logo_fab & next_btn
                logo_fab.setVisibility(View.GONE);
                next_btn.setVisibility(View.GONE);
                //動態新增fullVideoView
                fullVideoView = new FullVideoView(VideoActivity.this);
                fullVideoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory() + "/Movies//bwt02.mp4"));
                fullVideoView.setMediaController(new MediaController(VideoActivity.this));
                fullVideoView.requestFocus();
                fullVideoView.start();
                fullVideoView.setTag(1);

                video_flt.addView(fullVideoView);
                fullVideoView.bringToFront();

                //當影片撥放時，video_imv無法點擊
                video01_imv.setClickable(false);
                video02_imv.setClickable(false);
                video03_imv.setClickable(false);
                video04_imv.setClickable(false);

                fullVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        video_flt.removeView(fullVideoView);
                        fullVideoView.setTag(null);
                        logo_fab.setVisibility(View.VISIBLE);
                        next_btn.setVisibility(View.VISIBLE);
                        //當影片撥放時，video_imv無法點擊
                        video01_imv.setClickable(true);
                        video02_imv.setClickable(true);
                        video03_imv.setClickable(true);
                        video04_imv.setClickable(true);
                    }
                });
            }
        });

        video03_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放影片時隱藏logo_fab & next_btn
                logo_fab.setVisibility(View.GONE);
                next_btn.setVisibility(View.GONE);
                //動態新增fullVideoView
                fullVideoView = new FullVideoView(VideoActivity.this);
                fullVideoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory() + "/Movies//bwt03.mp4"));
                fullVideoView.setMediaController(new MediaController(VideoActivity.this));
                fullVideoView.requestFocus();
                fullVideoView.start();
                fullVideoView.setTag(1);

                video_flt.addView(fullVideoView);
                fullVideoView.bringToFront();

                //當影片撥放時，video_imv無法點擊
                video01_imv.setClickable(false);
                video02_imv.setClickable(false);
                video03_imv.setClickable(false);
                video04_imv.setClickable(false);

                fullVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        video_flt.removeView(fullVideoView);
                        fullVideoView.setTag(null);
                        logo_fab.setVisibility(View.VISIBLE);
                        next_btn.setVisibility(View.VISIBLE);
                        //當影片撥放時，video_imv無法點擊
                        video01_imv.setClickable(true);
                        video02_imv.setClickable(true);
                        video03_imv.setClickable(true);
                        video04_imv.setClickable(true);
                    }
                });
            }
        });

        video04_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放影片時隱藏logo_fab & next_btn
                logo_fab.setVisibility(View.GONE);
                next_btn.setVisibility(View.GONE);
                //動態新增fullVideoView
                fullVideoView = new FullVideoView(VideoActivity.this);
                fullVideoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory() + "/Movies//bwt04.mp4"));
                fullVideoView.setMediaController(new MediaController(VideoActivity.this));
                fullVideoView.requestFocus();
                fullVideoView.start();
                fullVideoView.setTag(1);

                video_flt.addView(fullVideoView);
                fullVideoView.bringToFront();

                //當影片撥放時，video_imv無法點擊
                video01_imv.setClickable(false);
                video02_imv.setClickable(false);
                video03_imv.setClickable(false);
                video04_imv.setClickable(false);

                fullVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        video_flt.removeView(fullVideoView);
                        fullVideoView.setTag(null);
                        logo_fab.setVisibility(View.VISIBLE);
                        next_btn.setVisibility(View.VISIBLE);
                        //當影片撥放時，video_imv無法點擊
                        video01_imv.setClickable(true);
                        video02_imv.setClickable(true);
                        video03_imv.setClickable(true);
                        video04_imv.setClickable(true);
                    }
                });
            }
        });
    }

            /*VideoActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(VideoActivity.this)
                        .load(R.drawable.video01)
                        .into(video01_imv);

                Glide.with(VideoActivity.this)
                        .load(R.drawable.video02)
                        .into(video02_imv);

                Glide.with(VideoActivity.this)
                        .load(R.drawable.video03)
                        .into(video03_imv);

                Glide.with(VideoActivity.this)
                        .load(R.drawable.video04)
                        .into(video04_imv);
            }
        });*/

                                        /*Glide.with(PartnerActivity.this)
                                        .load("http://192.168.0.172/WQP/img/b711.gif")
                                        .load(R.drawable.ball)
                                        .asBitmap()
                                        .skipMemoryCache(true)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .placeholder(R.drawable.b071)
                                        .error(R.drawable.b07)
                                        .into(partner_imv);*/

                                        /*Glide.with(HomepageActivity.this)
                                         .load(R.drawable.ball)
                                         .into(bk);*/

    @GlideModule
    public class MyAppGlideModule extends AppGlideModule {
        @Override
        public boolean isManifestParsingEnabled() {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        //當fullVideoView不存在時
        if (fullVideoView != null) {
            //當fullVideoView的Tag是1
            if (fullVideoView.getTag() != null) {
                video_flt.removeView(fullVideoView);
                fullVideoView.setTag(null);
                logo_fab.setVisibility(View.VISIBLE);
                next_btn.setVisibility(View.VISIBLE);
                //當影片撥放時，video_imv無法點擊
                video01_imv.setClickable(true);
                video02_imv.setClickable(true);
                video03_imv.setClickable(true);
                video04_imv.setClickable(true);
            } else {
                //當fullVideoView的Tag是null
                VideoActivity.this.finish();
            }
            //當fullVideoView存在時
        } else {
            VideoActivity.this.finish();
        }
    }
}