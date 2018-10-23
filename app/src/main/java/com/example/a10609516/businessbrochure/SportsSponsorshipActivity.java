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
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class SportsSponsorshipActivity extends AppCompatActivity {

    private RelativeLayout sport_rlt;
    private ImageView sport_imv;
    private FloatingActionMenu logo_fab;
    private FloatingActionButton home_fab, bwt_fab, aqu_fab, bin_fab;
    private Button last_btn, next_btn;

    //配合多解析度畫面，會調整Menu的高度
    private int WIDTH, HEIGHT;
    //讀取手機裝置資訊元件
    private DisplayMetrics mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_sponsorship);
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
        sport_rlt = (RelativeLayout) findViewById(R.id.sport_rlt);
        logo_fab = (FloatingActionMenu) findViewById(R.id.logo_fab);
        home_fab = (FloatingActionButton) findViewById(R.id.home_fab);
        bwt_fab = (FloatingActionButton) findViewById(R.id.bwt_fab);
        last_btn = (Button) findViewById(R.id.last_btn);
        next_btn = (Button) findViewById(R.id.next_btn);

        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(SportsSponsorshipActivity.this, VideoActivity.class);
                startActivity(next_intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(SportsSponsorshipActivity.this, F1Activity.class);
                startActivity(next_intent);
            }
        });

        home_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(SportsSponsorshipActivity.this, HomepageActivity.class);
                startActivity(bwt_intent);
            }
        });

        bwt_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(SportsSponsorshipActivity.this, CutscenesActivity.class);
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
        //設置fab menu圖片
        logo_fab.getMenuIconView().setImageResource(R.drawable.wqp_logo);
        //把view設置在最上層
        logo_fab.bringToFront();

        sport_imv = new ImageView(SportsSponsorshipActivity.this);
        sport_imv.setBackgroundResource(R.drawable.sport_1);
        LinearLayout.LayoutParams sport_pm = new LinearLayout.LayoutParams(WIDTH, HEIGHT);
        sport_imv.setLayoutParams(sport_pm);
        sport_rlt.addView(sport_imv);

        // 延遲1.5秒
        new Handler().postDelayed(new Runnable() {
            public void run() {
                sport_imv.setBackgroundResource(0);
                sport_imv.setBackgroundResource(R.drawable.sport_2);
                Animation am_sport = AnimationUtils.loadAnimation(SportsSponsorshipActivity.this, R.anim.anim_alpha);
                sport_rlt.startAnimation(am_sport);
            }
        }, 1 * 1500);


    }

    /**
     * 設置圖片的特效
     *
     * @return
     */
    private void ItemAnimation() {
        Animation am_sport = AnimationUtils.loadAnimation(this, R.anim.translate);
        sport_rlt.startAnimation(am_sport);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SportsSponsorshipActivity.this, VideoActivity.class);
        startActivity(intent);
    }

    protected void onRestart() {
        super.onRestart();
        sport_rlt.removeView(sport_imv);
        //動態新增ImageView
        addImageView();
        //設置圖片的特效
        ItemAnimation();
    }
}
