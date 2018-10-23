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
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class IntroductionActivity extends AppCompatActivity {

    private FrameLayout introduction_flt;
    private LinearLayout breadth_llt, depth_llt, experience_llt;
    private ImageView breadth_imv, depth_imv, experience_imv;
    private ImageView slash_imv;
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
        setContentView(R.layout.activity_introduction);
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
        introduction_flt = (FrameLayout) findViewById(R.id.introduction_flt);
        breadth_llt = (LinearLayout) findViewById(R.id.breadth_llt);
        depth_llt = (LinearLayout) findViewById(R.id.depth_llt);
        experience_llt = (LinearLayout) findViewById(R.id.experience_llt);
        breadth_imv = (ImageView) findViewById(R.id.breadth_imv);
        depth_imv = (ImageView) findViewById(R.id.depth_imv);
        experience_imv = (ImageView) findViewById(R.id.experience_imv);
        slash_imv = (ImageView) findViewById(R.id.slash_imv);
        last_btn = (Button) findViewById(R.id.last_btn);
        next_btn = (Button) findViewById(R.id.next_btn);
        logo_fab = (FloatingActionMenu) findViewById(R.id.logo_fab);
        home_fab = (FloatingActionButton) findViewById(R.id.home_fab);
        bwt_fab = (FloatingActionButton) findViewById(R.id.bwt_fab);

        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(IntroductionActivity.this, CutscenesInternationalActivity.class);
                startActivity(next_intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(IntroductionActivity.this, EnterpriseActivity.class);
                startActivity(next_intent);
            }
        });

        home_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(IntroductionActivity.this, HomepageActivity.class);
                startActivity(bwt_intent);
            }
        });

        bwt_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(IntroductionActivity.this, CutscenesActivity.class);
                startActivity(bwt_intent);
            }
        });

        /*introduction_flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation am_rotate = AnimationUtils.loadAnimation(IntroductionActivity.this, R.anim.rotate);
                breadth_imv.startAnimation(am_rotate);
                depth_imv.startAnimation(am_rotate);
                experience_imv.startAnimation(am_rotate);
            }
        });*/
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
    private void addImageView() {
        /*breadth_imv = new ImageView(IntroductionActivity.this);
        breadth_imv.setBackgroundResource(R.drawable.breadth);
        RelativeLayout.LayoutParams breadth_pm = new RelativeLayout.LayoutParams(WIDTH, HEIGHT);
        breadth_imv.setLayoutParams(breadth_pm);
        breadth_imv.setVisibility(View.GONE);

        depth_imv = new ImageView(IntroductionActivity.this);
        depth_imv.setBackgroundResource(R.drawable.depth);
        RelativeLayout.LayoutParams depth_pm = new RelativeLayout.LayoutParams(WIDTH, HEIGHT);
        depth_imv.setLayoutParams(depth_pm);
        depth_imv.setVisibility(View.GONE);

        experience_imv = new ImageView(IntroductionActivity.this);
        experience_imv.setBackgroundResource(R.drawable.experience);
        RelativeLayout.LayoutParams experience_pm = new RelativeLayout.LayoutParams(WIDTH, HEIGHT);
        experience_imv.setLayoutParams(experience_pm);
        experience_imv.setVisibility(View.GONE);

        introduction_flt.addView(breadth_imv);
        introduction_flt.addView(depth_imv);
        introduction_flt.addView(experience_imv);*/

        //設置fab menu圖片
        logo_fab.getMenuIconView().setImageResource(R.drawable.wqp_logo);
        //把view設置在最上層
        logo_fab.bringToFront();
        slash_imv.bringToFront();
        breadth_imv.bringToFront();
        depth_imv.bringToFront();
        experience_imv.bringToFront();
    }

    /**
     * 設置圖片的特效
     *
     * @return
     */
    private void ItemAnimation() {
        // 延遲0.2秒執行
        new Handler().postDelayed(new Runnable() {
            public void run() {
                breadth_llt.setVisibility(View.VISIBLE);
                /*Animation am_breadth = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, WIDTH * 1 / 4, HEIGHT / 2);
                am_breadth.setDuration(500);
                am_breadth.setRepeatCount(0);
                breadth_llt.setAnimation(am_breadth);
                am_breadth.startNow();*/
                Animation am_scale = AnimationUtils.loadAnimation(IntroductionActivity.this, R.anim.scale);
                breadth_llt.startAnimation(am_scale);
            }
        }, 1 * 200);

        // 延遲0.7秒執行
        new Handler().postDelayed(new Runnable() {
            public void run() {
                depth_llt.setVisibility(View.VISIBLE);
                /*Animation am_depth = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, WIDTH / 2, HEIGHT / 2);
                am_depth.setDuration(500);
                am_depth.setRepeatCount(0);
                depth_llt.setAnimation(am_depth);
                am_depth.startNow();*/
                Animation am_scale = AnimationUtils.loadAnimation(IntroductionActivity.this, R.anim.scale);
                depth_llt.startAnimation(am_scale);
            }
        }, 1 * 700);

        // 延遲1.2秒執行
        new Handler().postDelayed(new Runnable() {
            public void run() {
                experience_llt.setVisibility(View.VISIBLE);
                /*Animation am_experience = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, WIDTH * 3 / 4, HEIGHT / 2);
                am_experience.setDuration(500);
                am_experience.setRepeatCount(0);
                experience_llt.setAnimation(am_experience);
                am_experience.startNow();*/
                Animation am_scale = AnimationUtils.loadAnimation(IntroductionActivity.this, R.anim.scale);
                experience_llt.startAnimation(am_scale);
            }
        }, 1 * 1200);

        // 延遲1秒執行
        new Handler().postDelayed(new Runnable() {
            public void run() {
                slash_imv.setVisibility(View.VISIBLE);
                Animation am_slash = new AlphaAnimation(0.0f, 1.0f);
                am_slash.setDuration(800);
                slash_imv.setAnimation(am_slash);
                am_slash.startNow();
            }
        }, 1 * 1000);
    }

    protected void onRestart() {
        super.onRestart();
        breadth_llt.setVisibility(View.INVISIBLE);
        depth_llt.setVisibility(View.INVISIBLE);
        experience_llt.setVisibility(View.INVISIBLE);
        slash_imv.setVisibility(View.INVISIBLE);
        //設置圖片的特效
        ItemAnimation();
    }
}
