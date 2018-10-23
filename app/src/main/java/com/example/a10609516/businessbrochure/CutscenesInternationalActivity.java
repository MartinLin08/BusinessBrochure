package com.example.a10609516.businessbrochure;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class CutscenesInternationalActivity extends AppCompatActivity {

    private ImageView directions_imv, international_imv;
    private Button last_btn, next_btn;

    private static final int GOTO_MAIN_ACTIVITY = 0;

    //配合多解析度畫面，會調整Menu的高度
    private int WIDTH, HEIGHT;
    //讀取手機裝置資訊元件
    private DisplayMetrics mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutscenes_international);
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
    }

    /**
     * 動態取得 View 物件
     */
    private void InitFunction() {
        directions_imv = (ImageView) findViewById(R.id.directions_imv);
        international_imv = (ImageView) findViewById(R.id.international_imv);
        last_btn = (Button) findViewById(R.id.last_btn);
        next_btn = (Button) findViewById(R.id.next_btn);

        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(CutscenesInternationalActivity.this, CutscenesActivity.class);
                startActivity(next_intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(CutscenesInternationalActivity.this, IntroductionActivity.class);
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

        Animation am_directions = new AlphaAnimation(0.2f,1.0f);
        am_directions.setDuration(2000);
        am_directions.setRepeatCount(0);
        directions_imv.setAnimation(am_directions);
        am_directions.startNow();

        Animation am_international = new AlphaAnimation(0.0f,1.0f);
        am_international.setDuration(3500);
        am_international.setRepeatCount(0);
        international_imv.setAnimation(am_directions);
        am_international.startNow();
    }

    protected void onRestart() {
        super.onRestart();
        //設置圖片的特效
        ItemAnimation();
    }
}
