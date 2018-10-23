package com.example.a10609516.businessbrochure;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CutscenesActivity extends AppCompatActivity {

    private ImageView family_imv, since_imv, foryou_imv;
    private Button last_btn, next_btn;

    private static final int GOTO_MAIN_ACTIVITY = 0;

    //配合多解析度畫面，會調整Menu的高度
    private int WIDTH, HEIGHT;
    //讀取手機裝置資訊元件
    private DisplayMetrics mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutscenes);
        //取消NotificationBar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //取消ActionBar
        getSupportActionBar().hide();
        //動態取得 View 物件
        InitFunction();
        //獲取手機裝置資訊
        getWindowSize();
        //設置圖片的特效
        ItemAnimation();
        //5秒跳轉
        /*mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, 5000);*/
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    //將原本Activity的換成MainActivity
                    intent.setClass(CutscenesActivity.this, CutscenesInternationalActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 動態取得 View 物件
     */
    private void InitFunction() {
        family_imv = (ImageView) findViewById(R.id.family_imv);
        since_imv = (ImageView) findViewById(R.id.since_imv);
        foryou_imv = (ImageView) findViewById(R.id.foryou_imv);
        last_btn = (Button) findViewById(R.id.last_btn);
        next_btn = (Button) findViewById(R.id.next_btn);

        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(CutscenesActivity.this, HomepageActivity.class);
                startActivity(next_intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(CutscenesActivity.this, CutscenesInternationalActivity.class);
                startActivity(next_intent);
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
     * 設置圖片的特效
     *
     * @return
     */
    private void ItemAnimation() {

        Animation am_family = new TranslateAnimation(-WIDTH, 0, 0, 0);
        am_family.setDuration(1200);
        am_family.setRepeatCount(0);
        family_imv.setAnimation(am_family);
        am_family.startNow();

        Animation am_since = new TranslateAnimation(0, 0, HEIGHT, 0);
        am_since.setDuration(1800);
        am_since.setRepeatCount(0);
        since_imv.setAnimation(am_since);
        am_since.startNow();

        Animation am_foryou = AnimationUtils.loadAnimation(this, R.anim.alpha);
        foryou_imv.startAnimation(am_foryou);
    }

    protected void onRestart() {
        super.onRestart();
        //設置圖片的特效
        ItemAnimation();
    }
}
