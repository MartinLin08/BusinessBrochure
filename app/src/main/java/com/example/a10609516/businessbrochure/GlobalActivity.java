package com.example.a10609516.businessbrochure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.InputStream;

public class GlobalActivity extends AppCompatActivity {

    private FrameLayout global_flt;
    private LinearLayout global_llt;
    private ImageView global_imv, global_title_imv;
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
        setContentView(R.layout.activity_global);
        //取消NotificationBar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //取消ActionBar
        getSupportActionBar().hide();
        //動態取得 View 物件
        InitFunction();
        //獲取手機裝置資訊
        getWindowSize();
        //動態新增ImageView
        addImageView();
        //設置圖片的特效
        ItemAnimation();
    }

    /**
     * 動態取得 View 物件
     */
    private void InitFunction() {
        global_flt = (FrameLayout) findViewById(R.id.global_flt);
        global_llt = (LinearLayout) findViewById(R.id.global_llt);
        last_btn = (Button) findViewById(R.id.last_btn);
        next_btn = (Button) findViewById(R.id.next_btn);
        logo_fab = (FloatingActionMenu) findViewById(R.id.logo_fab);
        home_fab = (FloatingActionButton) findViewById(R.id.home_fab);
        bwt_fab = (FloatingActionButton) findViewById(R.id.bwt_fab);

        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(GlobalActivity.this, PartnerActivity.class);
                startActivity(next_intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(GlobalActivity.this, VideoActivity.class);
                startActivity(next_intent);
            }
        });

        home_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(GlobalActivity.this, HomepageActivity.class);
                startActivity(bwt_intent);
            }
        });

        bwt_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(GlobalActivity.this, CutscenesActivity.class);
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
     * 動態新增ImageView
     */
    private void addImageView (){
        global_llt.setBackgroundResource(R.drawable.global);

        global_imv = new ImageView(GlobalActivity.this);
        global_imv.setBackgroundResource(R.drawable.country);
        /*new DownloadImageTask(global_imv)   //((ImageView) findViewById(R.id.ivLoad))
                .execute("http://192.168.0.172/WQP/img/country.png"); // 載入圖片*/
        LinearLayout.LayoutParams global_pm = new LinearLayout.LayoutParams(WIDTH, HEIGHT);
        global_imv.setLayoutParams(global_pm);
        global_imv.setVisibility(View.GONE);
        global_llt.addView(global_imv);

        global_title_imv = new ImageView(GlobalActivity.this);
        global_title_imv.setBackgroundResource(R.drawable.global_title);
        FrameLayout.LayoutParams global_title_pm = new FrameLayout.LayoutParams(WIDTH, HEIGHT);
        global_title_pm.gravity = Gravity.BOTTOM;
        global_title_imv.setLayoutParams(global_title_pm);
        global_flt.addView(global_title_imv);

        //設置fab menu圖片
        logo_fab.getMenuIconView().setImageResource(R.drawable.wqp_logo);
        //把view設置在最上層
        logo_fab.bringToFront();
    }

    /**
     * 設置圖片的特效
     *
     * @return
     */
    private void ItemAnimation() {
        Animation am_global1 = new AlphaAnimation(0.0f, 1.0f);
        am_global1.setDuration(1800);
        am_global1.setRepeatCount(0);
        global_llt.setAnimation(am_global1);
        am_global1.startNow();

        Animation am_global2 = new TranslateAnimation(0, 0, HEIGHT, 0);
        am_global2.setDuration(1200);
        am_global2.setRepeatCount(0);
        global_title_imv.setAnimation(am_global2);
        am_global2.startNow();

        // 延遲1.5秒執行
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //顯示mMenu
                global_imv.setVisibility(View.VISIBLE);
                Animation am_global3 = AnimationUtils.loadAnimation(GlobalActivity.this, R.anim.anim_alpha);
                global_imv.startAnimation(am_global3);
            }
        }, 1 * 1200);

    }

    /* AsyncTask執行下載任務 */
    @SuppressLint("NewApi")
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream(); // 從網址上下載
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result); // 下載完成後載入結果
        }
    }

    protected void onRestart() {
        super.onRestart();
        global_imv.setVisibility(View.GONE);
        //設置圖片的特效
        ItemAnimation();
    }
}